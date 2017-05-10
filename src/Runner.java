import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class Runner extends PApplet {
	
	private boolean[] keys = new boolean[200];
	private List<Solid> solids;
	private Projectile projectile;
	private long last;
	private Point2D mouseHeld = null;
	
	private Runner() {
		
		solids = new ArrayList<Solid>();
		solids.add(new Solid(200, 130, 240, 140, this));
		projectile = new Projectile(220, 110, this);
		
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
	public void setup() {}

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
		projectile.draw();
		for (Solid  p : solids) {
			
			p.drawPlatform();
			
		}
		if (mouseHeld == null) {
			
			long deltaT = System.currentTimeMillis() - last;
			projectile.tick(deltaT);
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
			
		} else {
			
			if (mouseHeld != null) {
				
				projectile.setXVel((mouseX - mouseHeld.getX()) / 70);
				projectile.setYVel((mouseY - mouseHeld.getY()) / 70);
				
			}
			mouseHeld = null;
			
		}
		last = System.currentTimeMillis();

	}

}