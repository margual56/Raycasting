package app;

import java.awt.Graphics;
import java.util.ArrayList;

import geometry.Wall;
import maths.Ray;
import processing.core.PConstants;
import maths.Vector;

public class Player {
	public static final double FOV = Math.toRadians(60);
	public static final double PIXELS = 300;
	public static final double RESOLUTION = FOV / PIXELS;
	public static final double SPEED = 10;
	private Vector pos;
	private ArrayList<Ray> rays = new ArrayList<Ray>();
	double angle = 0;
	private double distanceToProjectionPlane;
	private Vector points[];
	private Wall walls[];

	public Player(double x, double y, Wall[] walls) {
		pos = new Vector(x, y);

		for (double a = -FOV / 2.0; a < FOV / 2.0; a += RESOLUTION) {
			rays.add(new Ray(x, y, a));
		}

		distanceToProjectionPlane = PIXELS / (2 * Math.tan(FOV));

		this.walls = walls;
	}

	public void lookAt(double x, double y) {
		angle = Math.atan2(y - pos.y, x - pos.x);

		rays.clear();
		for (double a = -FOV / 2.0 + angle; a < FOV / 2.0 + angle; a += RESOLUTION) {
			rays.add(new Ray(x, y, a));
		}
	}

	public void rotate(double ang) {
		this.angle += ang;

		for (Ray r : rays) {
			r.rotate(ang);
		}
	}

	public void move(double dir) {
		double movx = dir * Math.cos(angle) * SPEED;
		double movy = dir * Math.sin(angle) * SPEED;

		this.pos.add(new Vector(movx, movy));
	}

	public void update(double x, double y) {
		this.pos = new Vector(x, y);

		for (Ray r : rays) {
			r.update(x, y);
		}
	}

	public void update(double ang) {
		this.angle = ang;

		for (Ray r : rays) {
			r.update(ang);
		}
	}

	public void paint(Graphics g) {
		g.fill(255);
		g.noStroke();
		g.rectMode(PConstants.CENTER);
		g.rect(main.width / 2, main.height / 2, main.width, main.height);

		g.fill(0);
		for (Vector pt : points) {
			g.stroke(0, 50);
			g.line(pos.x, pos.y, pt.x, pt.y);

			g.noStroke();
			g.ellipse(pt.x, pt.y, 5, 5);
		}
	}

	public void showLight(Main main) {
		main.fill(255, 50);
		main.noStroke();

		for (Vector pt : points) {
			main.vertex(pt.x, pt.y);
		}

		main.endShape(Main.CLOSE);
	}

	private Vector[] calculatePoints() {
		ArrayList<Vector> points = new ArrayList<Vector>();

		for (Ray r : rays) {
			double record = Double.MAX_VALUE;
			Vector best = null;
			for (Wall wall : walls) {
				Vector pt = r.cast(wall);

				if (pt != null) {
					if (pt.dist(pos) < record) {
						record = pt.dist(pos);
						best = pt;
					}
				}
			}

			if (best != null) {
				points.add(best);
			}
		}

		return points.toArray(new Vector[0]);
	}

	public void render(Graphics g) {
		g.beginDraw();
		g.fill(0);
		g.noStroke();
		g.rectMode(PConstants.CENTER);
		g.rect(g.width / 2, g.height / 2, g.width, g.height);

		if (points.length == 0) {
			g.endDraw();
			return;
		}

		double w = g.width / points.length;

		int i = 0;
		for (Vector pt : points) {
			double x = (double) ((i + 0.5) * w);

			double dist = euclideanDistance(pt);

			double height = distanceToProjectionPlane * Wall.height /  Vector.dist(pos, pt);
			double brightness = Wall.getBrightness(Vector.dist(pos, pt));

			g.fill(brightness);
			g.rect(x, g.height / 2, w, height);

			i++;
		}

		g.endDraw();
	}

	public void minimap(Graphics g, Main main) {
		double xscale = (double) g.width / (double) main.width;
		double yscale = (double) g.height / (double) main.height;

		g.stroke(0);
		g.fill(255);
		g.pushMatrix();
		g.translate(pos.x * xscale, pos.y * yscale);
		g.ellipse(0, 0, 15, 15);

		g.stroke(255, 0, 0);
		g.line(0, 0, 30 * Main.cos(angle), 30 * Main.sin(angle));

		/*for (Vector pt : points) {
			g.line(0, 0, (pt.x-pos.x) * xscale, (pt.y -pos.y)* yscale);
		}*/
		
		g.popMatrix();
	}

	private double euclideanDistance(Vector p) {
		if (p == null)
			return Double.MAX_VALUE;

		double d = Vector.dist(pos, p);
		double a = Vector.angleBetween(pos, p) - this.angle;

		return d * Math.cos(a);
	}

	public void updateWalls(Wall[] walls) {
		this.walls = walls;
	}

	public void run() {
		points = calculatePoints();
	}
}
