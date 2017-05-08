import processing.core.PApplet;

public class Projectile {
	
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	private PApplet applet;
	
	public Projectile(int x, int y, PApplet applet) {
		
		setX(x);
		setY(y);
		this.applet = applet;
		
	}
	
	public void draw() {
		
		applet.fill(0);
		applet.ellipseMode(PApplet.CENTER);
		applet.ellipse(x, y, 5, 5);
		
	}

	public int getX() {
		
		return x;
		
	}

	public void setX(int x) {
		
		this.x = x;
		
	}

	public int getY() {
		
		return y;
		
	}

	public void setY(int y) {
		
		this.y = y;
		
	}

	public int getXVel() {
		
		return xVel;
		
	}

	public void setXVel(int xVel) {
		
		this.xVel = xVel;
		
	}

	public int getYVel() {
		
		return yVel;
		
	}

	public void setYVel(int yVel) {
		
		this.yVel = yVel;
		
	}

}
