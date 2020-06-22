package maths;

import app.Main;
import processing.core.PGraphics;
import processing.core.PVector;

public class Segment{
	private PVector a, b;

	public Segment(float x1, float y1, float x2, float y2) {
		a = new PVector(x1, y1);
		b = new PVector(x2, y2);
	}

	public Segment(PVector a, PVector b) {
		this.a = a.copy();
		this.b = b.copy();
	}

	public void show(Main main) {
		main.stroke(255);
		main.line(a.x, a.y, b.x, b.y);
	}
	
	public void show(PGraphics g, Main main) {
		float xscale = (float)g.width / (float)main.width;
		float yscale = (float)g.height / (float)main.height;
		g.line(a.x * xscale, a.y * yscale, b.x * xscale, b.y * yscale);
	}

	public PVector getA() {
		return a;
	}

	public void setA(PVector a) {
		this.a = a;
	}

	public PVector getB() {
		return b;
	}

	public void setB(PVector b) {
		this.b = b;
	}
}
