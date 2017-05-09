import processing.core.PApplet;

public class Projectile {
	
	/*
	 * x = x + vt
	 * y = y + vt + .5at^2
	 */
	
	public static float GRAVITY = 0.004f;
	private Point2D pos;
	private float xVel;
	private float yVel;
	private PApplet applet;
	
	public Projectile(float x, float y, PApplet applet) {
		
		pos = new Point2D(x, y);
		this.applet = applet;
		
	}
	
	public void setPosition(Point2D point) {
		
		pos = point;
		
	}
	
	public void draw() {
		
		applet.fill(0);
		applet.ellipseMode(PApplet.CENTER);
		applet.ellipse(pos.getX(), pos.getY(), 5, 5);
		
	}

	public float getXVel() {
		
		return xVel;
		
	}

	public void setXVel(float xVel) {
		
		this.xVel = xVel;
		
	}

	public float getYVel() {
		
		return yVel;
		
	}

	public void setYVel(float yVel) {
		
		this.yVel = yVel;
		
	}
	
	public void move(int x, int y) {
		
		pos.setX(x);
		pos.setY(y);
		
	}
	
	public void accelerate(float xVelBy, float yVelBy) {
		
		xVel += xVelBy;
		yVel += yVelBy;
		
	}
	
	public void decelerate() {
		
		xVel = 0;
		yVel = 0;
		
	}
	
	public void tick(long deltaT) {
		
		float deltaTFloat = deltaT;
		pos.addX(deltaTFloat * xVel);
		float deltaY = (deltaTFloat * yVel + (0.5f * GRAVITY * deltaTFloat * deltaTFloat));
		yVel = (float) Math.sqrt(yVel * yVel + (2f * GRAVITY * deltaTFloat)) * (yVel > 0f ? 1f : -1f);
		pos.addY(deltaY);
		
	}

}
