import processing.core.PApplet;

public class Projectile {
	
	/*
	 * x = x + vt
	 * y = y + vt + .5at^2
	 */
	
	public static float GRAVITY = 0.004f;
	private float x;
	private float y;
	private float xVel;
	private float yVel;
	private PApplet applet;
	
	public Projectile(float x, float y, PApplet applet) {
		
		setX(x);
		setY(y);
		this.applet = applet;
		
	}
	
	public void draw() {
		
		applet.fill(0);
		applet.ellipseMode(PApplet.CENTER);
		applet.ellipse(x, y, 5, 5);
		
	}

	public float getX() {
		
		return x;
		
	}

	public void setX(float x) {
		
		this.x = x;
		
	}

	public float getY() {
		
		return y;
		
	}

	public void setY(float y) {
		
		this.y = y;
		
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
		
		setX(x);
		setY(y);
		
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
		
		x += deltaT * xVel;
		float deltaY = (deltaT * yVel + (0.5f * GRAVITY * deltaT * deltaT));
		yVel = (float) Math.sqrt(yVel * yVel + (2 * GRAVITY * deltaY));
		y += deltaY;
		
	}
	
	public void collide() {
		
		
		
	}
	
	@Override
	public Projectile clone() {
		
		Projectile clone = new Projectile(getX(), getY(), applet);
		clone.xVel = xVel;
		clone.yVel = yVel;
		return clone;
		
	}

}
