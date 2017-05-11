import processing.core.PApplet;

public class Solid {

	private Point2D one;
	private Point2D two;
	private PApplet applet;

	public Solid(int x, int y, int x2, int y2, PApplet applet) {

		one = new Point2D(x, y);
		two = new Point2D(x2, y2);
		this.applet = applet;
		fixPoints();
		
	}
	
	public Point2D getPositionOne() {
		
		return one;
		
	}
	
	public Point2D getPositionTwo() {
		
		return two;
		
	}
	
	public void drawPlatform() {

		applet.fill(0);
		applet.rectMode(PApplet.CORNERS);
		applet.rect(one.getX(), one.getY(), two.getX(), two.getY());
		
	}

	public boolean isInside(float x, float y) {

		return x > one.getX() && x < two.getX() && y > one.getY() - 5 && y < two.getY();
		
	}

	public void fixPoints() {
		
		float temp;
		if (two.getY() < one.getY()) {

			temp = two.getY();
			two.setY(one.getY());
			one.setY(temp);
			
		}
		if (one.getX() > two.getX()) {

			temp = two.getX();
			two.setX(one.getX());
			one.setX(temp);
			
		}
		
	}
	
}