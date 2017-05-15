public class CollisionPoint2D extends Point2D implements Comparable<CollisionPoint2D> {
	
	private float time;
	private float newXVel;
	private float newYVel;
	private boolean touchingX;
	private boolean touchingY;

	public CollisionPoint2D(float x, float y, float newXVel, float newYVel, float time) {
		
		super(x, y);
		setTime(time);
		setNewXVel(newXVel);
		setNewYVel(newYVel);
		
	}
	
	public CollisionPoint2D(Point2D point, float newXVel, float newYVel, float time) {
		
		super(point.getX(), point.getY());
		setTime(time);
		setNewXVel(newXVel);
		setNewYVel(newYVel);
		
	}
	
	public void setTime(float time) {
		
		this.time = time;
		
	}
	
	public float getTime() {
		
		return time;
		
	}

	@Override
	public int compareTo(CollisionPoint2D other) {
		
		return (int) (getTime() - other.getTime());
		
	}

	public float getNewXVel() {
		
		return newXVel;
		
	}

	public void setNewXVel(float newXVel) {
		
		this.newXVel = newXVel;
		
	}

	public float getNewYVel() {
		
		return newYVel;
		
	}

	public void setNewYVel(float newYVel) {
		
		this.newYVel = newYVel;
		
	}
	
	@Override
	public String toString() {
		
		return "(" + getX() + ", " + getY() + ", t = " + getTime() + ")";
		
	}

	public boolean isTouchingX() {
		
		return touchingX;
		
	}

	public void setTouchingX(boolean touchingX) {
		
		this.touchingX = touchingX;
		
	}

	public boolean isTouchingY() {
		
		return touchingY;
		
	}

	public void setTouchingY(boolean touchingY) {
		
		this.touchingY = touchingY;
		
	}

}
