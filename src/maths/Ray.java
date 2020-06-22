package maths;

import processing.core.PApplet;
import processing.core.PVector;

public class Ray extends Segment {
	@SuppressWarnings("unused")
	private float angle, initialAngle;
	
	public Ray(float x1, float y1, float x2, float y2) {
		super(x1, y1, x1, y2);
		
		angle = PVector.angleBetween(getA(), getB());
	}

	public Ray(PVector a, PVector b) {
		super(a, b);

		angle = PVector.angleBetween(a, b);
		initialAngle = angle;
	}
	
	public Ray(PVector a, float angle) {
		super(a, PVector.fromAngle(angle));
		this.angle = angle;
		initialAngle = angle;
		
	}
	
	public Ray(float x, float y, float angle) {
		super(new PVector(x, y), PVector.fromAngle(-angle));
		this.angle = angle;
		initialAngle = angle;
	}
	
	public void lookAt(float x, float y) {
		this.setB(new PVector(x, y).sub(this.getA()).normalize());
	}
	
	public PVector cast(Segment s) {
		float x1 = s.getA().x;
		float y1 = s.getA().y;
		float x2 = s.getB().x;
		float y2 = s.getB().y;

		float x3 = this.getA().x;
		float y3 = this.getA().y;
		float x4 = x3 + this.getB().x;
		float y4 = y3 + this.getB().y;
		
		float denominator = (x1 - x2) * (y3 - y4) - (y1 - y2)*(x3 - x4);
		
		if(denominator == 0)
			return null;
		
		float t = ((x1 - x3)*(y3 - y4) - (y1 - y3)*(x3 - x4))/denominator;
		float u = -((x1 - x2)*(y1 - y3) - (y1 - y2)*(x1 - x3))/denominator;
		
		if(0<t && t<1 && u>0) {
			return new PVector(x1 + t*(x2 - x1), y1 + t*(y2 - y1));
		}
		
		return null;
	}
	
	public void update(float x, float y) {
		this.setA(new PVector(x, y));
		//this.setB(this.getA().add(PVector.fromAngle(angle)));
	}
	
	public void update(float a) {
		this.angle = initialAngle + a;
		this.setB(PVector.fromAngle(angle));
	}
	
	public void rotate(float a) {
		this.angle += a;
		this.setB(PVector.fromAngle(angle));
	}
	
	
	public void show(PApplet main) {
		main.stroke(255);
		
		main.pushMatrix();
		main.translate(this.getA().x, this.getA().y);
		main.line(0, 0, this.getB().x*100, this.getB().y*100);
		main.popMatrix();
	}
}
