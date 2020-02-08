package ex;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
public class Interpreter {
    final static double INIT_X = 100;
    final static double INIT_Y = 100;
    final static double INIT_DIRECTION = 180;
    private int a;
    double x;
    double y;
    double direction;
    GraphicsContext gc;    void repeat(ArrayList<Token> tokens, int a, int time, Tortue tortue) {
    	a  = a+2;
    	int repeat_end = 0;
    	for(int j = 0; j<time;j++) {
    		for(int i = a; i<tokens.size();i++) {	
	    			if(tokens.get(i).getValue() instanceof String) {
		        		if(tokens.get(i).getValue().contains("left") ) {
		        			System.out.println("left" + tokens.get(i+1).getValue());
		        			tortue.setOrientation(-Integer.parseInt(tokens.get(i+1).getValue()));
		        		}
		        		if(tokens.get(i).getValue().contains("right") ) {
		        			System.out.println("right" + tokens.get(i+1).getValue());
		        			tortue.setOrientation(Integer.parseInt(tokens.get(i+1).getValue()));
		        		}
		        		if(tokens.get(i).getValue().contains("forward")) {
		        			System.out.println("forward" + tokens.get(i+1).getValue());
		        			tortue.avance(Integer.parseInt(tokens.get(i+1).getValue()));
		        		}
		        		if(tokens.get(i).getValue().contains("]")) {
		        			repeat_end = i;
		        			break;
		        		}
		        		if(tokens.get(i).getValue().contains("repeat")) {
		        			System.out.println("repeat" + tokens.get(i+1).getValue());
		        			repeat(tokens,i,Integer.parseInt(tokens.get(i+1).getValue()),tortue);
		        			while(!tokens.get(i).getValue().contains("]")) {
		        				i = i+1;
		        			}
		        		}
		        		if(tokens.get(i).getValue().contains("[")) {		        		}	    	}
	    }    	}
    	this.a = repeat_end;
    	System.out.println("end");    }
    void interpreter(ArrayList<Token> tokens, GraphicsContext gc) {
        this.gc = gc;
        x = INIT_X;
        y = INIT_Y;
        direction = INIT_DIRECTION;
        Tortue tortue = new Tortue(gc);
        tortue.setX(x);
        tortue.setY(y);
        for(int i=0;i<tokens.size();i++) {
        	if(tokens.get(i).getValue() instanceof String) {
        		if(tokens.get(i).getValue().contains("left") ) {
        			System.out.println("left" + tokens.get(i+1).getValue());
        			tortue.setOrientation(-Integer.parseInt(tokens.get(i+1).getValue()));
        		}
        		if(tokens.get(i).getValue().contains("right") ) {
        			System.out.println("right" + tokens.get(i+1).getValue());
        			tortue.setOrientation(Integer.parseInt(tokens.get(i+1).getValue()));
        		}
        		if(tokens.get(i).getValue().contains("forward")) {
        			System.out.println("forward" + tokens.get(i+1).getValue());
        			tortue.avance(Integer.parseInt(tokens.get(i+1).getValue()));
        		}
        		if(tokens.get(i).getValue().contains("repeat")) {
        			System.out.println("repeat" + tokens.get(i+1).getValue());
        			repeat(tokens,i, Integer.parseInt(tokens.get(i+1).getValue()),tortue);
        			i = a;
        		}
        	}
        }
    }
}