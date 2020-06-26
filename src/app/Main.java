package app;

import java.awt.Graphics;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import geometry.*;

public class Main extends JPanel implements KeyListener {
	private static final long serialVersionUID = 890835106829127139L;
	public static final int width = 1920, height = 1080;

	public static double limit;

	Wall walls[];
	Player p;
	Graphics g, minimap;
	double mDeltaX, prevmX;
	Robot robot;

	public void init() {
		limit = width;
		walls = new Wall[3];
		walls[0] = new Wall(Math.random() * (limit - 1), Math.random() * (height - 1), Math.random() * (limit - 1),
				Math.random() * (height - 1));
		walls[1] = new Wall(Math.random() * (limit - 1), Math.random() * (height - 1), Math.random() * (limit - 1),
				Math.random() * (height - 1));
		walls[2] = new Wall(Math.random() * (limit - 1), Math.random() * (height - 1), Math.random() * (limit - 1),
				Math.random() * (height - 1));
		/*walls[3] = new Wall(0, 0, limit-1, 0);
		walls[4] = new Wall(0, 0, 0, height-1);
		walls[5] = new Wall(limit-1, height-1, limit-1, 0);
		walls[6] = new Wall(limit-1, height-1, 0, height-1);*/

		p = new Player(width / 2, height / 2, walls);

		mDeltaX = 0;
		prevmX = 0;

		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		p.run();
		p.paint(g);
		
		/*g.background(41);

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
		image(minimap, 0, 0);*/
	}

	public static double map(double x, double minx, double maxx, double miny, double maxy) {
		return (x - maxx) / (maxx - minx) * (maxy - miny) + miny;
	}

	public static double clamp(double x, double min, double max) {
		if (x < min)
			return min;

		if (x > max)
			return max;

		return x;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyChar() == ' ') {
			walls[0] = new Wall(Math.random() * (limit), Math.random() * (height), Math.random() * (limit),
					Math.random() * (height));
			walls[1] = new Wall(Math.random() * (limit), Math.random() * (height), Math.random() * (limit),
					Math.random() * (height));
			walls[2] = new Wall(Math.random() * (limit), Math.random() * (height), Math.random() * (limit),
					Math.random() * (height));

			p.updateWalls(walls);

			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent key) {
		switch (key.getKeyChar()) {
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

		repaint();
	}
}
