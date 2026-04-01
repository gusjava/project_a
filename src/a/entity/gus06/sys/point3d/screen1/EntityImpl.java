package a.entity.gus06.sys.point3d.screen1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;


public class EntityImpl implements Entity, P, I, V {

	public String creationDate() {return "20160421";}

	
	public static final double DEFAULT_VISION = Math.PI*0.25;
	public static final Color DEFAULT_PIXELCOLOR = Color.WHITE;
	public static final int DEFAULT_PIXELSIZE = 1;
	
	
	private ScreenJPanel panel;
	private double vision;
	private Color pixelColor;
	private int pixelSize;

	

	public EntityImpl() throws Exception
	{
		vision = DEFAULT_VISION;
		pixelColor = DEFAULT_PIXELCOLOR;
		pixelSize = DEFAULT_PIXELSIZE;
		
		panel = new ScreenJPanel();
	}


	
	public Object i() throws Exception
	{return panel;}



	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("vision")) {vision = Math.PI*Double.parseDouble((String)obj);return;}
		if(key.equals("pixelColor")) {pixelColor = (Color)obj;return;}
		if(key.equals("pixelSize")) {pixelSize = Integer.parseInt((String)obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	

	public void p(Object obj) throws Exception
	{
		List list = (List)obj;
		int number = list.size();
		
		Point[] points = new Point[number];
		for(int i=0;i<number;i++)
		{
			double[] point3D = (double[]) list.get(i);
			points[i] = buildPoint(point3D);
		}
		panel.repaint(points);
	}


	
	
	public Point buildPoint(double[] v)
	{
		double ux = calculAngle(v[1],v[0]);
		double uz = calculAngle(v[1],v[2]);
		
		if(Math.abs(ux)>vision || Math.abs(uz)>vision) return null;
		
		double vision2 = vision*2;
		double x = (vision+ux)/vision2;
		double y = (vision-uz)/vision2;
		return new Point(x,y);
	}
	
	
	private double calculAngle(double y, double xz)
	{
		if(y==0 || xz==0) return 0;
		if(y>0) return atan(xz/y);
		if(xz>0) return Math.PI/2 - atan(y/xz);
		return -1*Math.PI/2 - atan(y/xz);
	}

	
	private double atan(double angle)
	{return Math.atan(angle);}
	
	
	private class Point
	{
		private double x;
		private double y;
		public Point(double x, double y)
		{this.x=x;this.y=y;}
	}
	
	
	
	
	private class ScreenJPanel extends JPanel
	{	
		private Point[] points;
		
		public void repaint(Point[] points)
		{
			this.points = points;
			repaint();
		}
		
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(points!=null) paintPoints((Graphics2D) g);
		}
		
		private void paintPoints(Graphics2D g2)
		{
			g2.setColor(pixelColor);
			for(int i=0;i<points.length;i++)
			if(points[i]!=null)
			{
				int X = (int) (points[i].x*getWidth());
				int Y = (int) (points[i].y*getHeight());
				g2.fillRect(X,Y,pixelSize,pixelSize);
			}
		}
	}
}