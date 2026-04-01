package a.entity.gus06.graphics.draw.point2d.arrow1;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import a.framework.*;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20161202";}
	
	public static final double ARROW_COEF = 8;
	

	private Graphics2D g2;
	private double arrowCoef = ARROW_COEF;
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("graphics2D")) {g2 = (Graphics2D) obj;return;}
		if(key.equals("arrowCoef")) {arrowCoef = Double.parseDouble((String) obj);return;}
		throw new Exception("Unknown key: "+key);
	}


	
	public void p(Object obj) throws Exception
	{
		Point2D[] p = (Point2D[]) obj;
		drawArrow(p[0],p[1]);
	}
	
	
	private void drawArrow(Point2D p1, Point2D p2)
	{
		drawLine(p1,p2);
		if(p1.equals(p2)) return;

		Point2D pc = getCenter(p1,p2);
		double d = getDistance(p1,p2);
		double k = arrowCoef/d;

		Point2D pa1 = getArrowPoint1(p1,p2,pc,k);
		Point2D pa2 = getArrowPoint2(p1,p2,pc,k);
		
		drawLine(pc,pa1);
		drawLine(pc,pa2);
	}


	
	private Point2D getCenter(Point2D p1, Point2D p2)
	{
		double x = (p1.getX() + p2.getX())/2;
		double y = (p1.getY() + p2.getY())/2;
		return new Point2D.Double(x,y);
	}
	
	
	private double getDistance(Point2D p1, Point2D p2)
	{
		double dx = p1.getX() - p2.getX();
		double dy = p1.getY() - p2.getY();
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	
	private Point2D getArrowPoint1(Point2D p1, Point2D p2, Point2D pc, double k)
	{
		double x = pc.getX() + k*(p1.getX()-p2.getX()+p1.getY()-p2.getY());
		double y = pc.getY() + k*(p2.getX()-p1.getX()+p1.getY()-p2.getY());
		return new Point2D.Double(x,y);
	}
	
	private Point2D getArrowPoint2(Point2D p1, Point2D p2, Point2D pc, double k)
	{
		double x = pc.getX() + k*(p1.getX()-p2.getX()+p2.getY()-p1.getY());
		double y = pc.getY() + k*(p1.getX()-p2.getX()+p1.getY()-p2.getY());
		return new Point2D.Double(x,y);
	}
	
	private void drawLine(Point2D p1, Point2D p2)
	{g2.draw(new Line2D.Double(p1,p2));}
}
