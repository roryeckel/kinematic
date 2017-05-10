import processing.core.PApplet;

public class Projectile {
	
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
		
		// if it will collide, teleport directly to the collision, bounce velocities,
		// subtract deltaT, and recurse
		float deltaTFloat = deltaT;
		pos.addX(deltaTFloat * xVel);
		float deltaY = (deltaTFloat * yVel + (0.5f * GRAVITY * deltaTFloat * deltaTFloat));
		yVel += GRAVITY * deltaTFloat;
		pos.addY(deltaY);
		
	}
	
	public String xEquation(long deltaT) {
		
		return "x(final) = " + (int) pos.getX() + " + " + (int) deltaT + " * " + (int) xVel;
		
	}
	
	public String yEquation(long deltaT) {
		
		return "y(final) = " + (int) pos.getY() + " + " + (int) deltaT + " * " + (int) yVel
				+ " + (.5 * " + GRAVITY + " * " + (int) deltaT + "^2)";
		
	}
	
	@Override
	public String toString() {
		
		return "Position: " + pos + ", Speed: (" + xVel + ", " + yVel + ")";
		
	}

}
