import processing.core.PApplet;

public class Projectile implements Cloneable {
	
	public static float GRAVITY = 0.004f;
	private Point2D pos;
	private float xVel;
	private float yVel;
	private PApplet applet;
	
	public Projectile(float x, float y, PApplet applet) {
		
		pos = new Point2D(x, y);
		this.applet = applet;
		
	}
	
	@Override
	public Projectile clone() {
		
		Projectile clone = new Projectile(pos.getX(), pos.getY(), applet);
		clone.xVel = xVel;
		clone.yVel = yVel;
		return clone;
		
	}
	
	public Point2D getPosition() {
		
		return pos;
		
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
	
	public void tick(float deltaT) {
		
		// if it will collide, teleport directly to the collision, bounce velocities,
		// subtract deltaT, and recurse
		pos.addX(deltaT * xVel);
		float deltaY = (deltaT * yVel + (0.5f * GRAVITY * deltaT * deltaT));
		yVel += GRAVITY * deltaT;
		pos.addY(deltaY);
		
	}
	
	public Point2D findCollision(Solid solid) {
		
		float[] collisionTimes = new float[4];
		Point2D[] collisions = new Point2D[4]; // Left, right, top, bottom
		
		// Left side
		float deltaX = solid.getPositionOne().getX() - getPosition().getX();
		float deltaT = deltaX / getXVel();
		float deltaY = (deltaT * getYVel() + (0.5f * GRAVITY * deltaT * deltaT));
		float Y = getPosition().getY() + deltaY;
		if (solid.getPositionOne().getY() < Y
				&& Y < solid.getPositionTwo().getY()) {
			
			collisions[0] = new Point2D(getPosition().getX() + deltaX, Y);
			collisionTimes[0] = deltaT;
			
		}
		
		// Right side
		deltaX = solid.getPositionTwo().getX() - getPosition().getX();
		deltaT = deltaX / getXVel();
		deltaY = (deltaT * getYVel() + (0.5f * GRAVITY * deltaT * deltaT));
		Y = getPosition().getY() + deltaY;
		if (solid.getPositionOne().getY() < Y
				&& Y < solid.getPositionTwo().getY()) {
			
			collisions[1] = new Point2D(getPosition().getX() + deltaX, Y);
			collisionTimes[1] = deltaT;
					
		}
		
		// Top
		
		return null;
		
	}
	
	public float timeTo(Point2D end) {
		
		float deltaY = end.getY() - pos.getY();
		float timeX = (end.getX() - pos.getX()) / xVel;
		float deltaYCompare = ((2f * yVel + (GRAVITY * timeX)) / 2f * timeX);
		if (floatEquals(deltaY, deltaYCompare)) {
			
			return timeX;
			
		} else {
			
			return -1;
			
		}
		
	}
	
	public String xEquation(long deltaT) {
		
		return "x(final) = " + (int) pos.getX() + " + " + (int) deltaT + " * " + (int) xVel;
		
	}
	
	public String yEquation(long deltaT) {
		
		return "y(final) = " + (int) pos.getY() + " + " + (int) deltaT + " * " + (int) yVel
				+ " + (.5 * " + GRAVITY + " * " + (int) deltaT + "^2)";
		
	}
	
	private static boolean floatEquals(float one, float two) {
		
		return Math.abs(two - one) < 0.00008f;
		
	}
	
	@Override
	public String toString() {
		
		return "Position: " + pos + ", Speed: (" + xVel + ", " + yVel + ")";
		
	}

}
