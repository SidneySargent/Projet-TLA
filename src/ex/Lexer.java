package ex;

import java.util.ArrayList;

public class Lexer {

	static Integer transitions[][] = {
			//             espace lettre chiffre   [    ]     autre
			/*  0 */    {      0,     1,      2,   3,   4,	  null      },
			/*  1 */    {    201,     1,      1, 201, 201,	  null      },
			/*  2 */    {    202,   202,      2, 202, 202,	  null      },
			/*  3 */    {    203,   203,    203, 204, 203,	  null      },
			/*  4 */    {    205,   205,    205, 206, 205,	  null      },

			// 201 accepte identifiant				(goBack : oui)
			// 202 accepte entier					(goBack : oui)
			// 203 accepte [						(goBack : non)
			// 204 accepte ]						(goBack : non)
			// 101 accepte ;                        (goBack : non)
			// 102 accepte +                        (goBack : non)
			// 103 accepte -                        (goBack : non)
			// 104 accepte *                        (goBack : non)
			// 105 accepte /                        (goBack : non)
			// 106 accepte (                        (goBack : non)
			// 107 accepte )                        (goBack : non)
			// 108 accepte {                        (goBack : non)
			// 109 accepte }                        (goBack : non)

			// 201 accepte identifiant ou mot clé   (goBack : oui)
			// 202 accepte entier                   (goBack : oui)
			// 203 accepte =                        (goBack : oui)
			// 204 accepte ==                       (goBack : non)
			// 205 accepte <                        (goBack : oui)
			// 206 accepte <=                       (goBack : non)
			// 207 accepte >                        (goBack : oui)
			// 208 accepte >=                       (goBack : non)
			// 209 accepte !                        (goBack : oui)
			// 210 accepte !=                       (goBack : non)

	};

	static final int ETAT_INITIAL = 0;

	private int indiceSymbole(Character c) {
		if (c == null) return 0;
		if (Character.isWhitespace(c)) return 0;
		if (Character.isLetter(c)) return 1;
		if (Character.isDigit(c)) return 2;
		if (c == '[') return 3;
		if (c == ']') return 4;
		return 5;
	}

	public ArrayList<Token> lexer(SourceReader sr) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		String buf="";
		int etat = ETAT_INITIAL;
		while (true) {
			Character c = sr.lectureSymbole();
			Integer e = transitions[etat][indiceSymbole(c)];
			if (e == null) {
				System.out.println(" pas de transition depuis état " + etat + " avec symbole " + c);
				return new ArrayList<Token>(); // renvoie une liste vide
			}
			if (e >= 100) {
				if (e == 201) {
					System.out.println("accepte identifiant " + buf);
					tokens.add(new Token(TokenClass.ident,buf));
				}
				if (e == 202) {
					System.out.println("accepte int "+buf);
					tokens.add(new Token(TokenClass.intval,buf));
				}
				
				if (e == 203) {
					System.out.println("accepte [");
					tokens.add(new Token(TokenClass.leftBrace,"["));
				}
				if (e == 205) {
					System.out.println("accepte ]");
					tokens.add(new Token(TokenClass.rightBrace,"]"));
				}
				
				etat = 0;
				buf = "";
			} else {
				etat = e;
				if (etat>0) buf = buf + c;
			}
			if (c==null) break;
		}
		return tokens;
	}

}
