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
		projectile = new Projectile(170, 160, this);
		projectile.setXVel(0.2f);
		projectile.setYVel(-0.1f);
		
	}

	public static void main(String[] args) {

		PApplet.runSketch(new String[] { "" }, new Runner());

	}

	@Override
	public void keyPressed() {
		
		if (key == DELETE) {
			
			for (Solid solid : solids) {
				
				if (solid.isInside(mouseX, mouseY)) {
					
					solids.remove(solid);
					return;
					
				}
				
			}
			
		}
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
		fill(0);
		
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
			
			if (solids.size() > 0) {
				
				solids.get(solids.size() - 1).fixPoints();
				
			}
			modifyLast = false;
			
		}
		projectile.draw();
		Projectile future = projectile.clone();
		for (int f = 0; f < 100; f++) {
			
			future.tick(10, solids);
			future.drawSmall();
			
		}
		if (mouseHeld == null) {
			
			long deltaT = System.currentTimeMillis() - last;
			projectile.tick(deltaT, solids);
			text("Sim by Rory Eckel\n" + projectile.xEquation(deltaT) +
					"\n" + projectile.yEquation(deltaT) + "\n\n" + projectile, 1, 10);
			
		} else {
			
			projectile.move(mouseX, mouseY);
			for (Solid solid : solids) {
				
				projectile.findCollision(solid);
				
			}
			
		}
		if (mousePressed) {
			
			if (mouseHeld == null) {
								
				mouseHeld = new Point2D(mouseX, mouseY);
				
			}
			stroke(0);
			line(mouseX, mouseY, mouseHeld.getX(), mouseHeld.getY());
			noStroke();
			projectile.setXVel((mouseX - mouseHeld.getX()) / 70);
			projectile.setYVel((mouseY - mouseHeld.getY()) / 70);
			
		} else {
			
			mouseHeld = null;
			
		}
		last = System.currentTimeMillis();

	}

}