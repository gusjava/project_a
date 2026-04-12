package a.entity.gus06.appli.fishtank.gui.maingui;

public class Rock extends Element implements CONST {
	
	private double radius = 0;
	private int time = 0;

	public Rock(double x, double y) {
		super(x, y);
	}

	
	public void update() {
		time++;
		if(radius<RADIUS) radius+=INC;
	}
	
	public boolean isOver() {
		return time>=LIFETIME;
	}
	
	public double getRadius() {
		return radius;
	}
}
