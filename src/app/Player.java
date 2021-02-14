package app;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import geometry.Wall;
import maths.Ray;
import maths.JVector;

public class Player {
	public static final double FOV = Math.toRadians(60);
	public static final double PIXELS = 300;
	public static final double RESOLUTION = FOV / PIXELS;
	public static final double SPEED = 10;
	private JVector pos;
	private ArrayList<Ray> rays = new ArrayList<Ray>();
	double angle = 0;
	private double distanceToProjectionPlane;
	private JVector points[];
	private Wall walls[];

	public Player(double x, double y, Wall[] walls) {
		pos = new JVector(x, y);

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

		this.pos.add(new JVector(movx, movy));
	}

	public void update(double x, double y) {
		this.pos = new JVector(x, y);

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
		g.setColor(Color.white);
		g.fillRect(0, 0, Main.width, Main.height);

		for (JVector pt : points) {
			g.setColor(Color.black);
			g.fillOval((int) pt.x, (int) pt.y, 5, 5);

			g.setColor(new Color(0, 0, 0, 50));
			g.drawLine((int) pos.x, (int) pos.y, (int) pt.x, (int) pt.y);
		}
	}

	public void showLight(Graphics g) {
		g.setColor(new Color(255, 255, 255, 50));

		for (int i = 1; i < points.length; i++) {
			g.drawLine((int) points[i - 1].x, (int) points[i - 1].y, (int) points[i].x, (int) points[i].y);
		}

		//Close the shape
		g.drawLine((int) points[points.length - 1].x, (int) points[points.length - 1].y, (int) points[0].x,
				(int) points[0].y);
	}

	private JVector[] calculatePoints() {
		ArrayList<JVector> points = new ArrayList<JVector>();

		for (Ray r : rays) {
			double record = Double.MAX_VALUE;
			JVector best = null;
			for (Wall wall : walls) {
				JVector pt = r.cast(wall);

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

		return points.toArray(new JVector[0]);
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Main.width, Main.height);

		if (points.length == 0) {
			return;
		}

		int w = (int) Math.round((double) Main.width / points.length);

		int i = 0;
		for (JVector pt : points) {
			int x = (int) ((i + 0.5) * w);

			//double dist = euclideanDistance(pt);

			int height = (int) Math.round((double) distanceToProjectionPlane * Wall.height / JVector.dist(pos, pt));
			int brightness = (int) Math.round(Wall.getBrightness(JVector.dist(pos, pt)));

			g.setColor(new Color(brightness, brightness, brightness));
			g.fillRect(x - w / 2, (int) Math.round(Main.height / 2.0 - height / 2.0), w, height);

			i++;
		}
	}

	public void minimap(Graphics g) {
		double xscale = (double) g.getClipBounds().width / (double) Main.width;
		double yscale = (double) g.getClipBounds().height / (double) Main.height;

		int xoffset = (int)Math.round(pos.x * xscale);
		int yoffset = (int)Math.round(pos.y * yscale);
		//translate(, pos.y * yscale);
		/*g.stroke(0);
		g.fill(255);*/
		
		g.setColor(Color.white);
		g.fillOval(xoffset, yoffset, 15, 15);
		g.setColor(Color.black);
		g.drawOval(xoffset, yoffset, 15, 15);

		g.setColor(new Color(255, 0, 0));
		g.drawLine(xoffset, yoffset, (int)Math.round(xoffset + 30 * Math.cos(angle)), (int)Math.round(yoffset + 30 * Math.sin(angle)));

		/*for (Vector pt : points) {
			g.line(0, 0, (pt.x-pos.x) * xscale, (pt.y -pos.y)* yscale);
		}*/
	}

	private double euclideanDistance(JVector p) {
		if (p == null)
			return Double.MAX_VALUE;

		double d = JVector.dist(pos, p);
		double a = JVector.angleBetween(pos, p) - this.angle;

		return d * Math.cos(a);
	}

	public void updateWalls(Wall[] walls) {
		this.walls = walls;
	}

	public void run() {
		points = calculatePoints();
	}
}
