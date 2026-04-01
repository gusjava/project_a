package a.entity.gus06.sys.point3d.screen3;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class EntityImpl implements Entity, P, I, V {

	public String creationDate() {return "20160421";}


	public static final double DEFAULT_VISION = Math.PI*0.25;
	public static final Color DEFAULT_PIXELCOLOR = Color.WHITE;
	public static final int DEFAULT_PIXELSIZE = 1;
	
	
	
	private Service paintBehindFrame;
	private P imagePainter;
	
	private ScreenJPanel panel;
	private double vision;
	private Color pixelColor;
	private int pixelSize;

	private List list_points;
	private List list_colors;
	
	
	


	public EntityImpl() throws Exception
	{
		paintBehindFrame = Outside.service(this,"gus06.awt.component.paint.behindframe");
		
		vision = DEFAULT_VISION;
		pixelColor = DEFAULT_PIXELCOLOR;
		pixelSize = DEFAULT_PIXELSIZE;
		
		panel = new ScreenJPanel();
		imagePainter = (P) paintBehindFrame.t(panel);
	}


	
	public Object i() throws Exception
	{return panel;}



	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("vision")) {vision = Math.PI*Double.parseDouble((String)obj);return;}
		if(key.equals("pixelColor")) {pixelColor = (Color)obj;return;}
		if(key.equals("pixelColors")) {list_colors = (List)obj;return;}
		if(key.equals("pixelSize")) {pixelSize = Integer.parseInt((String)obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	

	public void p(Object obj) throws Exception
	{
		list_points = (List) obj;
		int number = list_points.size();
		
		Point[] p = new Point[number];
		double[] d = new double[number];
		
		double longest = 0;
		double nearest = Double.MAX_VALUE;
		
		for(int i=0;i<number;i++)
		{
			double[] point3D = (double[]) list_points.get(i);
			p[i] = buildPoint(point3D);
			d[i] = computeDistance(point3D);
			if(d[i]>longest) longest = d[i];
			if(d[i]<nearest) nearest = d[i];
		}
		
		double ecart = longest-nearest;
		AlphaComposite[] ac = new AlphaComposite[number];
		
		for(int i=0;i<number;i++)
		if(p[i]!=null)
		{
			float alpha = (float)((longest-d[i])/ecart);
			ac[i] = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
		}
		panel.repaint(p,ac);
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
	
	
	private double computeDistance(double[] v)
	{return Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]);}

	
	private Color pixelColor(int index)
	{
		if(list_colors==null) return null;
		if(list_colors.size()>=index) return null;
		return (Color) list_colors.get(index);
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
	
	
	
	
	private void paintImage(Graphics2D g2)
	{
		try{imagePainter.p(g2);}
		catch(Exception e)
		{Outside.err(this,"paintImage(Graphics2D)",e);}
	}
	
	
	
	
	
	private class ScreenJPanel extends JPanel
	{
		private Point[] p;
		private AlphaComposite[] ac;
		
		
		public void repaint(Point[] p, AlphaComposite[] ac)
		{
			this.p = p;
			this.ac = ac;
			repaint();
		}
		
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			paintImage(g2);
			
			if(p!=null)
			{
				if(list_colors==null) paintPoints(g2);
				else paintPoints2(g2);
			}
		}
		
		private void paintPoints(Graphics2D g2)
		{
			g2.setColor(pixelColor);
			for(int i=0;i<p.length;i++)
			if(p[i]!=null)
			{
				int X = (int) (p[i].x*getWidth());
				int Y = (int) (p[i].y*getHeight());
				g2.setComposite(ac[i]);
				g2.fillRect(X,Y,pixelSize,pixelSize);
			}
		}
		
		private void paintPoints2(Graphics2D g2)
		{
			for(int i=0;i<p.length;i++)
			if(p[i]!=null)
			{
				int X = (int) (p[i].x*getWidth());
				int Y = (int) (p[i].y*getHeight());
				g2.setColor(pixelColor(i));
				g2.setComposite(ac[i]);
				g2.fillRect(X,Y,pixelSize,pixelSize);
			}
		}
	}
}