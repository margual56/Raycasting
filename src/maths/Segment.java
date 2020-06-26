package maths;

import java.awt.Color;
import java.awt.Graphics;

import app.Main;

public class Segment{
	private Vector a, b;

	public Segment(double x1, double y1, double x12, double y2) {
		a = new Vector(x1, y1);
		b = new Vector(x12, y2);
	}

	public Segment(Vector a, Vector b) {
		this.a = a.copy();
		this.b = b.copy();
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.drawLine((int)a.x, (int)a.y, (int)b.x, (int)b.y);
	}
	
	public void paint(Graphics g, Main main) {
		double xscale = (double)g.getClipBounds().width / (double)main.width;
		double yscale = (double)g.getClipBounds().height / (double)main.height;
		g.drawLine((int)(a.x * xscale), (int)(a.y * yscale), (int)(b.x * xscale), (int)(b.y * yscale));
	}

	public Vector getA() {
		return a;
	}

	public void setA(Vector a) {
		this.a = a;
	}

	public Vector getB() {
		return b;
	}

	public void setB(Vector vector) {
		this.b = vector;
	}
}
