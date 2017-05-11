import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class Runner extends PApplet {
	
	private boolean[] keys = new boolean[200];
	private List<Solid> solids;
	private Projectile projectile;
	private long last;
	private Point2D mouseHeld = null;
	boolean modifyLast = false;
	
	private Runner() {
		
		solids = new ArrayList<Solid>();
		solids.add(new Solid(200, 130, 300, 170, this));
		projectile = new Projectile(170, 160, this);
		projectile.setXVel(0.2f);
		projectile.setYVel(-0.1f);
		
	}

	public static void main(String[] args) {

		PApplet.runSketch(new String[] { "" }, new Runner());

	}

	@Override
	public void keyPressed() {

		if ((int) key < 200) {

			keys[(int) key] = true;

		}

	}

	@Override
	public void keyReleased() {

		if ((int) key < 200) {

			keys[(int) key] = false;

		}

	}

	@Override
	public void setup() {
		
		noStroke();
		
	}

	@Override
	public void settings() {
		
		size(480, 360, P2D);

	}

	@Override
	public void draw() {
		
		if (last == 0) {
			
			last = System.currentTimeMillis();
			
		}
		background(200);
		for (Solid  p : solids) {
			
			p.drawPlatform();
			projectile.findCollision(p);
			
		}
		if (keys[(int) ' ']) {
			
			if (!modifyLast) {
				
				solids.add(new Solid(mouseX, mouseY, mouseX, mouseY, this));
				
			} else {
				
				Point2D solid = solids.get(solids.size() - 1).getPositionTwo();
				solid.setX(mouseX);
				solid.setY(mouseY);
				
			}
			modifyLast = true;
			
		} else {
			
			modifyLast = false;
			
		}
		projectile.draw();
		if (mouseHeld == null) {
			
			long deltaT = System.currentTimeMillis() - last;
			projectile.tick(deltaT, solids);
			text("Sim by Rory Eckel\n" + projectile.xEquation(deltaT) +
					"\n" + projectile.yEquation(deltaT) + "\n\n" + projectile, 1, 10);
			
		} else {
			
			projectile.move(mouseX, mouseY);
			
		}
		if (mousePressed) {
			
			if (mouseHeld == null) {
								
				mouseHeld = new Point2D(mouseX, mouseY);
				
			}
			line(mouseX, mouseY, mouseHeld.getX(), mouseHeld.getY());
			projectile.setXVel((mouseX - mouseHeld.getX()) / 70);
			projectile.setYVel((mouseY - mouseHeld.getY()) / 70);
			
		} else {
			
			mouseHeld = null;
			
		}
		last = System.currentTimeMillis();

	}

}