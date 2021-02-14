package maths;

import java.awt.Color;
import java.awt.Graphics;
import app.Main;

public class Segment{
	private JVector a, b;

	public Segment(double x1, double y1, double x12, double y2) {
		a = new JVector(x1, y1);
		b = new JVector(x12, y2);
	}

	public Segment(JVector a, JVector b) {
		this.a = a.copy();
		this.b = b.copy();
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.drawLine((int)a.x, (int)a.y, (int)b.x, (int)b.y);
	}
	
	public void paint(Graphics g, Main main) {
		double xscale = (double)g.getClipBounds().width / (double)Main.width;
		double yscale = (double)g.getClipBounds().height / (double)Main.height;
		g.drawLine((int)(a.x * xscale), (int)(a.y * yscale), (int)(b.x * xscale), (int)(b.y * yscale));
	}

	public JVector getA() {
		return a;
	}

	public void setA(JVector a) {
		this.a = a;
	}

	public JVector getB() {
		return b;
	}

	public void setB(JVector vector) {
		this.b = vector;
	}
}
