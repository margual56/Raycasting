package app;

import processing.core.*;

import java.awt.AWTException;
import java.awt.Robot;

import geometry.*;

public class Main extends PApplet {

	public static void main(String args[]) {
		PApplet.main("app.Main");
	}

	public static float limit;

	public void settings() {
		size(1920, 1080);
		limit = width;
	}

	Wall walls[];
	Player p;
	PGraphics g, minimap;
	float mDeltaX, prevmX;
	Robot robot;

	public void setup() {
		walls = new Wall[3];
		walls[0] = new Wall(random(limit-1), random(height-1), random(limit-1), random(height-1));
		walls[1] = new Wall(random(limit-1), random(height-1), random(limit-1), random(height-1));
		walls[2] = new Wall(random(limit-1), random(height-1), random(limit-1), random(height-1));
		walls[3] = new Wall(0, 0, limit-1, 0);
		walls[4] = new Wall(0, 0, 0, height-1);
		walls[5] = new Wall(limit-1, height-1, limit-1, 0);
		walls[6] = new Wall(limit-1, height-1, 0, height-1);

		p = new Player(width / 2, height / 2, walls);

		g = createGraphics(width, height);
		minimap = createGraphics(200, 200);

		mDeltaX = 0;
		prevmX = 0;
		
		update();
	}
	
	public void draw() {
		if (keyPressed) {
			switch (key) {
			case 'w':
				p.move(1);
				break;
			case 's':
				p.move(-1);
				break;
			case 'a':
				p.rotate(-0.1f);
				break;
				
			case 'd':
				p.rotate(0.1f);
				break;
			}

			update();
		}
	}

	public void update() {
		background(41);
		
		minimap.beginDraw();
		minimap.fill(255);
		minimap.rectMode(PConstants.CORNER);
		minimap.rect(0, 0, minimap.width, minimap.height);
		minimap.strokeWeight(4);
		minimap.stroke(0);
		minimap.fill(0);
		for (Wall wall : walls)
			wall.show(minimap, this);
		
		minimap.strokeWeight(1);
		
		p.run();
		p.minimap(minimap, this);
		minimap.endDraw();
		p.render(g);

		image(g, 0, 0);
		image(minimap, 0, 0);
	}

	public void keyPressed() {
		if (key == ' ') {
			walls[0] = new Wall(random(limit), random(height), random(limit), random(height));
			walls[1] = new Wall(random(limit), random(height), random(limit), random(height));
			walls[2] = new Wall(random(limit), random(height), random(limit), random(height));

			p.updateWalls(walls);
		}
	}
}
