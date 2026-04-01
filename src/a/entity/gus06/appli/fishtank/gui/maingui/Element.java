package a.entity.gus06.appli.fishtank.gui.maingui;

public class Element {

	public double x;
	public double y;
	
	public Element(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double distance(Element o) {
		return Math.sqrt(distance2(o));
	}
	
	public double distance2(Element o) {
		return (o.x-x)*(o.x-x) + (o.y-y)*(o.y-y);
	}
}
