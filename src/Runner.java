import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class Runner extends PApplet {
	
	private boolean[] keys = new boolean[200];
	private List<Solid> solids;
	private Projectile projectile;
	private long last;
	
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
		if (keyPressed && keys[(int) ' ']) {
			
			projectile.move(mouseX, mouseY);
			projectile.decelerate();
			
		} else {
			
			projectile.tick(System.currentTimeMillis() - last);
			
		}
		last = System.currentTimeMillis();

	}

}