package app;

import java.util.ArrayList;

import geometry.Wall;
import maths.Ray;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class Player {
	public static final float FOV = 60;
	public static final float PIXELS = 300;
	public static final float RESOLUTION = FOV / PIXELS;
	public static final float SPEED = 10;
	private PVector pos;
	private ArrayList<Ray> rays = new ArrayList<Ray>();
	private float angle = 0, distanceToProjectionPlane;
	private PVector points[];
	private Wall walls[];

	public Player(float x, float y, Wall[] walls) {
		pos = new PVector(x, y);

		for (double a = -FOV / 2.0; a < FOV / 2.0; a += RESOLUTION) {
			rays.add(new Ray(x, y, Main.radians((float) a)));
		}

		distanceToProjectionPlane = PIXELS / (2 * Main.tan(Main.radians(FOV)));

		this.walls = walls;
	}

	public void lookAt(float x, float y) {
		angle = Main.atan2(y - pos.y, x - pos.x);

		rays.clear();
		for (double a = -FOV / 2.0 + angle; a < FOV / 2.0 + angle; a += RESOLUTION) {
			rays.add(new Ray(x, y, Main.radians((float) a)));
		}
	}

	public void rotate(float ang) {
		this.angle += ang;

		for (Ray r : rays) {
			r.rotate(ang);
		}
	}

	public void move(float dir) {
		float movx = dir * Main.cos(angle) * SPEED;
		float movy = dir * Main.sin(angle) * SPEED;

		this.pos.add(movx, movy);
	}

	public void update(float x, float y) {
		this.pos = new PVector(x, y);

		for (Ray r : rays) {
			r.update(x, y);
		}
	}

	public void update(float ang) {
		this.angle = ang;

		for (Ray r : rays) {
			r.update(ang);
		}
	}

	public void show(Main main) {
		main.fill(255);
		main.noStroke();
		main.rectMode(PConstants.CENTER);
		main.rect(main.width / 2, main.height / 2, main.width, main.height);

		main.fill(0);
		for (PVector pt : points) {
			main.stroke(0, 50);
			main.line(pos.x, pos.y, pt.x, pt.y);

			main.noStroke();
			main.ellipse(pt.x, pt.y, 5, 5);
		}
	}

	public void showLight(Main main) {
		main.fill(255, 50);
		main.noStroke();

		for (PVector pt : points) {
			main.vertex(pt.x, pt.y);
		}

		main.endShape(Main.CLOSE);
	}

	private PVector[] calculatePoints() {
		ArrayList<PVector> points = new ArrayList<PVector>();

		for (Ray r : rays) {
			float record = Float.MAX_VALUE;
			PVector best = null;
			for (Wall wall : walls) {
				PVector pt = r.cast(wall);

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

		return points.toArray(new PVector[0]);
	}

	public void render(PGraphics g) {
		g.beginDraw();
		g.fill(0);
		g.noStroke();
		g.rectMode(PConstants.CENTER);
		g.rect(g.width / 2, g.height / 2, g.width, g.height);

		if (points.length == 0) {
			g.endDraw();
			return;
		}

		float w = g.width / points.length;

		int i = 0;
		for (PVector pt : points) {
			float x = (float) ((i + 0.5) * w);

			float dist = euclideanDistance(pt);

			float height = distanceToProjectionPlane * Wall.height /  PVector.dist(pos, pt);
			float brightness = Wall.getBrightness(PVector.dist(pos, pt));

			g.fill(brightness);
			g.rect(x, g.height / 2, w, height);

			i++;
		}

		g.endDraw();
	}

	public void minimap(PGraphics g, Main main) {
		float xscale = (float) g.width / (float) main.width;
		float yscale = (float) g.height / (float) main.height;

		g.stroke(0);
		g.fill(255);
		g.pushMatrix();
		g.translate(pos.x * xscale, pos.y * yscale);
		g.ellipse(0, 0, 15, 15);

		g.stroke(255, 0, 0);
		g.line(0, 0, 30 * Main.cos(angle), 30 * Main.sin(angle));

		/*for (PVector pt : points) {
			g.line(0, 0, (pt.x-pos.x) * xscale, (pt.y -pos.y)* yscale);
		}*/
		
		g.popMatrix();
	}

	private float euclideanDistance(PVector p) {
		if (p == null)
			return Float.MAX_VALUE;

		float d = PVector.dist(pos, p);
		float a = PVector.angleBetween(pos, p) - this.angle;

		return d * Main.cos(Main.radians(a));
	}

	public void updateWalls(Wall[] walls) {
		this.walls = walls;
	}

	public void run() {
		points = calculatePoints();
	}
}
