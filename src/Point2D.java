public class Point2D {
	
	private float x;
	private float y;
	
	public Point2D(float x, float y) {
		
		setX(x);
		setY(y);
		
	}
	
	public void addX(float add) {
		
		x+= add;
		
	}
	
	public void addY(float add) {
		
		y += add;
		
	}
	
	public float getX() {
		
		return x;
		
	}
	
	public float getY() {
		
		return y;
		
	}
	
	public void setX(float x) {
		
		this.x = x;
		
	}
	
	public void setY(float y) {
		
		this.y = y;
		
	}
	
	@Override
	public String toString() {
		
		return "(" + x + ", " + y + ")";
		
	}

}
