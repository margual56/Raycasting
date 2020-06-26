package geometry;

import app.Main;
import maths.Segment;
import maths.Vector;

public class Wall extends Segment {
	public static final double height = 1080;
	
	public Wall(double d, double e, double f, double g) {
		super(d, e, f, g);
	}

	public Wall(Vector a, Vector b) {
		super(a, b);
	}
	
	public static double getHeight(double distance, Main main) {
		return Main.map(distance, 0, Main.limit*2, Main.height, 0);
	}
	
	public static double getBrightness(double d) {
		return Main.map(Math.pow(d, 2.2f), 0, Math.pow(Main.limit*2, 2), 255, 0);
	}
	
	/*@Override
	public void show(Main main) {
		main.stroke(50);
		main.fill(0);
		
		main.pushMatrix();
		main.translate(this.getA().x, this.getA().y);
		main.rotate(Vector.angleBetween(this.getA(), this.getB()));
		main.rect(0, 0, Main.sqrt((double) (Math.pow(this.getA().x-this.getB().x, 2) + Main.pow(this.getA().y-this.getB().y, 2))), 5);
		main.popMatrix();
	}*/
}