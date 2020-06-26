package maths;

import java.awt.Color;
import java.awt.Graphics;

public class Ray extends Segment {
	@SuppressWarnings("unused")
	private double angle, initialAngle;
	
	public Ray(double x1, double y1, double x2, double y2) {
		super(x1, y1, x1, y2);
		
		angle = Vector.angleBetween(getA(), getB());
	}

	public Ray(Vector a, Vector b) {
		super(a, b);

		angle = Vector.angleBetween(a, b);
		initialAngle = angle;
	}
	
	public Ray(Vector a, double angle) {
		super(a, Vector.fromAngle(angle));
		this.angle = angle;
		initialAngle = angle;
		
	}
	
	public Ray(double x, double y, double a) {
		super(new Vector(x, y), Vector.fromAngle(-a));
		this.angle = a;
		initialAngle = a;
	}
	
	public void lookAt(double x, double y) {
		Vector v = new Vector(x, y);
		v.sub(this.getA());
		v.normalize();
		this.setB(v);
	}
	
	public Vector cast(Segment s) {
		double x1 = s.getA().x;
		double y1 = s.getA().y;
		double x2 = s.getB().x;
		double y2 = s.getB().y;

		double x3 = this.getA().x;
		double y3 = this.getA().y;
		double x4 = x3 + this.getB().x;
		double y4 = y3 + this.getB().y;
		
		double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2)*(x3 - x4);
		
		if(denominator == 0)
			return null;
		
		double t = ((x1 - x3)*(y3 - y4) - (y1 - y3)*(x3 - x4))/denominator;
		double u = -((x1 - x2)*(y1 - y3) - (y1 - y2)*(x1 - x3))/denominator;
		
		if(0<t && t<1 && u>0) {
			return new Vector(x1 + t*(x2 - x1), y1 + t*(y2 - y1));
		}
		
		return null;
	}
	
	public void update(double x, double y) {
		this.setA(new Vector(x, y));
		//this.setB(this.getA().add(Vector.fromAngle(angle)));
	}
	
	public void update(double a) {
		this.angle = initialAngle + a;
		this.setB(Vector.fromAngle(angle));
	}
	
	public void rotate(double a) {
		this.angle += a;
		this.setB(Vector.fromAngle(angle));
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		
		double x = this.getA().x + this.getB().x*100;
		double y = this.getA().y + this.getB().y*100;
		
		g.drawLine((int)this.getA().x, (int)this.getA().y, (int)x, (int)y);
		
		
	}
}
