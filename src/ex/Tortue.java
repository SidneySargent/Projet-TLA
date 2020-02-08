package ex;


import javafx.scene.canvas.GraphicsContext;

public class Tortue {

	private double orientation = 180;
	private double x;
	private double y;
	GraphicsContext gc;
	public Tortue(GraphicsContext gc) {
		this.gc = gc;
	}
	void Tortue(String instruction) {
		
	}

	public double getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = this.orientation - orientation;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	void avance(double longueur) {
        double cibleX = x + Math.sin(orientation*Math.PI*2/360) * longueur;
        double cibleY = y + Math.cos(orientation*Math.PI*2/360) * longueur;
        gc.strokeLine(x, y, cibleX, cibleY);
        x = cibleX;
        y = cibleY;
    }
	
}
