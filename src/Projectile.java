import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
		
		applet.ellipseMode(PApplet.CENTER);
		applet.ellipse(pos.getX(), pos.getY(), 5, 5);
		
	}
	
	public void drawSmall() {
		
		applet.ellipseMode(PApplet.CENTER);
		applet.ellipse(pos.getX(), pos.getY(), 3, 3);
		
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
	
	public void tick(float deltaT, List<Solid> solids) {
		
		boolean collided = false;
		CollisionPoint2D point = null;
		for (Solid solid : solids) {
			
			point = findCollision(solid)[0];
			if (point != null && deltaT >= point.getTime()) {
				
				setPosition(point);
				xVel = point.getNewXVel();
				yVel = point.getNewYVel();
				deltaT-= point.getTime();
				collided = true;
				break;
				
			}
			
		}
		if (!collided && deltaT > 0) {
			
			pos.addX(deltaT * xVel);
			float deltaY = (deltaT * yVel + (0.5f * GRAVITY * deltaT * deltaT));
			yVel += GRAVITY * deltaT;
			pos.addY(deltaY);
			
		}
		
	}
	
	public CollisionPoint2D[] findCollision(Solid solid) {
		
		CollisionPoint2D[] collisions = new CollisionPoint2D[4];
		
		// Left side
		float deltaX = solid.getPositionOne().getX() - getPosition().getX();
		float deltaT = deltaX / getXVel();
		float deltaY = 0;
		float Y = 0;
		if (deltaT > 0) {
			
			deltaY = (deltaT * getYVel() + (0.5f * GRAVITY * deltaT * deltaT));
			Y = getPosition().getY() + deltaY;
			if (solid.getPositionOne().getY() < Y
					&& Y < solid.getPositionTwo().getY()) {
				
				collisions[0] = new CollisionPoint2D(getPosition().getX() + deltaX, Y, -xVel, yVel, deltaT);
				
			}
			
		}
		
		// Right side
		deltaX = solid.getPositionTwo().getX() - getPosition().getX();
		deltaT = deltaX / getXVel();
		if (deltaT > 0) {
			
			deltaY = (deltaT * getYVel() + (0.5f * GRAVITY * deltaT * deltaT));
			Y = getPosition().getY() + deltaY;
			if (solid.getPositionOne().getY() < Y
					&& Y < solid.getPositionTwo().getY()) {
				
				collisions[1] = new CollisionPoint2D(getPosition().getX() + deltaX, Y, -xVel, yVel, deltaT);
						
			}
			
		}
		
		// Top
		deltaY = solid.getPositionOne().getY() - getPosition().getY();
		deltaT = (float) ((Math.sqrt(2f * GRAVITY * deltaY + (yVel * yVel)) - yVel) / GRAVITY);
		float X = 0;
		if (deltaT > 0) {
			
			deltaX = xVel * deltaT;
			Y = getPosition().getY() + deltaY;
			X = getPosition().getX() + deltaX;
			if (solid.getPositionOne().getX() < X
					&& X < solid.getPositionTwo().getX()) {
				
				collisions[2] = new CollisionPoint2D(getPosition().getX() + deltaX, Y, xVel, -yVel, deltaT);
				
			}
			
		}
		
		// Bottom
		deltaY = solid.getPositionTwo().getY() - getPosition().getY();
		deltaT = (float) ((-Math.sqrt(2f * GRAVITY * deltaY + (yVel * yVel)) - yVel) / -GRAVITY);
		if (deltaT > 0) {
			
			deltaX = xVel * deltaT;
			Y = getPosition().getY() + deltaY;
			X = getPosition().getX() + deltaX;
			if (solid.getPositionOne().getX() < X
					&& X < solid.getPositionTwo().getX()) {
				
				collisions[3] = new CollisionPoint2D(getPosition().getX() + deltaX, Y, xVel, -yVel, deltaT);
				
			}
			
		}
		
		arraySort(collisions);
		return collisions;
		
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
	
	private static void arraySort(CollisionPoint2D[] vals) {
		
		Arrays.sort(vals, new Comparator<CollisionPoint2D>() {
			
	        @Override
	        public int compare(CollisionPoint2D o1, CollisionPoint2D o2) {
	        	
	        	if (o1 == null && o2 == null) {
	        		
	                return 0;
	                
	            }
	            if (o1 == null) {
	            	
	                return 1;
	                
	            }
	            if (o2 == null) {
	            	
	                return -1;
	                
	            }
	            return o1.compareTo(o2);
	            
	        }});
		
	}
	
	public String xEquation(long deltaT) {
		
		return "x(final) = " + (int) pos.getX() + " + " + (int) deltaT + " * " + (int) xVel;
		
	}
	
	public String yEquation(long deltaT) {
		
		return "y(final) = " + (int) pos.getY() + " + " + (int) deltaT + " * " + (int) yVel
				+ " + (.5 * " + GRAVITY + " * " + (int) deltaT + "^2)";
		
	}
	
	private static boolean floatEquals(float one, float two) {
		
		return Math.abs(two - one) < 0.1f;
		
	}
	
	@Override
	public String toString() {
		
		return "Position: " + pos + ", Speed: (" + xVel + ", " + yVel + ")";
		
	}

}
