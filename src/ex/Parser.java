package ex;

import java.util.ArrayList;

public class Parser {

    private int pos;
    private int profondeur;
    private ArrayList<Token> tokens;

    public void parser(ArrayList<Token> tokens) throws Exception {
        this.tokens = tokens;
        pos = 0;
        S();
        System.out.println("Fin atteinte = " + (pos == tokens.size()));
    }

    /*

    méthodes des symboles non terminaux

      */

    private void S() throws Exception {

        if (getTokenClass() == TokenClass.ident) {
            // production S -> A

            profondeur++;
            A();
            return;
        }
        throw new Exception("ident attendu");
    }

  
    
    private void A() throws Exception {

    	if(isEOF()) {
    		return;
    	}
    	
    	if (getTokenClass() == TokenClass.ident) {

            // production A -> ident intval A | ident intval [ A ] A | epsilon

            Token tokIdentVal = getToken();
            printNode(tokIdentVal.getValue());
            
            if (getTokenClass() == TokenClass.intval) {
                Token tokIntVal = getToken();
                printNode(tokIntVal.getValue());
                //profondeur--;
                A();
                return;
            }
            throw new Exception("intVal attendu");
        }
        else if (getTokenClass() == TokenClass.leftBrace)
        {
    		profondeur--;
    		Token tokLBrace = getToken();
            printNode(tokLBrace.getValue());
            profondeur++;
            A();
        }
        else if (getTokenClass() == TokenClass.rightBrace)
        {
        	profondeur++;
        	Token tokRBrace = getToken();
        	printNode(tokRBrace.getValue());
        	A();
        }
        else System.out.println("pas trouv�");

    }


    /*

    autres méthodes

     */

    private boolean isEOF() {
        return pos >= tokens.size();
    }

    /*
     * Retourne la classe du prochain token à lire
     * SANS AVANCER au token suivant
     */
    private TokenClass getTokenClass() {
        if (pos >= tokens.size()) {
            return null;
        } else {
            return tokens.get(pos).getCl();
        }
    }

    /*
     * Retourne le prochain token à lire
     * ET AVANCE au token suivant
     */
    private Token getToken() {
        if (pos >= tokens.size()) {
            return null;
        } else {
            Token current = tokens.get(pos);
            pos++;
            return current;
        }
    }

    private void printNode(String s) {
        for(int i=0;i<profondeur;i++) {
            System.out.print("    ");
        }
        System.out.println(s);
    }
}
