import processing.core.PApplet;

public class Solid {

	private int x, y, x2, y2;
	private PApplet applet;

	public Solid(int x, int y, int x2, int y2, PApplet applet) {

		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.applet = applet;
		fixPoints();
		
	}

	public int getX() {
		
		return x;
		
	}

	public int getY() {
		
		return y;
		
	}

	public int getX2() {
		
		return x2;
		
	}

	public int getY2() {
		
		return y2;
		
	}

	public void setX(int x) {
		
		this.x = x;
		
	}

	public void setX2(int x) {
		
		this.x2 = x;
		
	}

	public void setY(int y) {
		
		this.y = y;
		
	}

	public void setY2(int y) {
		this.y2 = y;
	}

	public void drawPlatform() {

		applet.fill(0);
		applet.rect(this.x, this.y, this.x2, this.y2);
		
	}

	public boolean isInside(float x, float y) {

		return x > this.x && x < this.x2 && y > this.y - 5 && y < this.y2;
		
	}

	public void fixPoints() {
		
		int temp;
		if (this.y2 < this.y) {

			temp = this.y2;
			this.y2 = this.y;
			this.y2 = temp;
			
		}
		if (this.x > this.x2) {

			temp = this.x2;
			this.x2 = this.x;
			this.x = temp;
			
		}
		
	}
	
}