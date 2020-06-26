package geometry;

import app.Main;
import maths.Segment;
import maths.Vector;

public class Wall extends Segment {
	public static final float height = 1080;
	
	public Wall(float x1, float y1, float x2, float y2) {
		super(x1, y1, x2, y2);
	}

	public Wall(Vector a, Vector b) {
		super(a, b);
	}
	
	public static float getHeight(float distance, Main main) {
		return Main.map(distance, 0, Main.limit*2, main.height, 0);
	}
	
	public static float getBrightness(float distance) {
		return Main.map(Main.pow(distance, 2.2f), 0, Main.pow(Main.limit*2, 2), 255, 0);
	}
	
	/*@Override
	public void show(Main main) {
		main.stroke(50);
		main.fill(0);
		
		main.pushMatrix();
		main.translate(this.getA().x, this.getA().y);
		main.rotate(Vector.angleBetween(this.getA(), this.getB()));
		main.rect(0, 0, Main.sqrt((float) (Math.pow(this.getA().x-this.getB().x, 2) + Main.pow(this.getA().y-this.getB().y, 2))), 5);
		main.popMatrix();
	}*/
}