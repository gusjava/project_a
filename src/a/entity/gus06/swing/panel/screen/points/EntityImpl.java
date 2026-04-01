package a.entity.gus06.swing.panel.screen.points;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.Color;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.awt.Graphics;

public class EntityImpl implements Entity, I, P, G, V {

	public String creationDate() {return "20170107";}
	
	public static final Color COLOR = Color.WHITE;
	public static final int[] DIM = new int[]{1000,1000};
	public static final int POINTSIZE = 2;
	public static final int CROSSLENGTH = 0;


	private Service findColor;
	private Service findDoubleArray;

	private JPanel1 panel;
	private Map map;
	
	private int pointsize = POINTSIZE;
	private int crosslength = CROSSLENGTH;
	private int[] dim = DIM;
	private Color color = COLOR;
	

	public EntityImpl() throws Exception
	{
		findColor = Outside.service(this,"gus06.find.color");
		findDoubleArray = Outside.service(this,"gus06.find.doublearray");
		
		panel = new JPanel1();
	}


	public Object g() throws Exception
	{return map;}


	public Object i() throws Exception
	{return panel;}



	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		panel.repaint();
	}
	

	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("dim"))
		{
			dim = (int[]) obj;
			panel.repaint();
			return;
		}
		if(key.equals("pointsize"))
		{
			pointsize = Integer.parseInt(""+obj);
			panel.repaint();
			return;
		}
		if(key.equals("crosslength"))
		{
			crosslength = Integer.parseInt(""+obj);
			panel.repaint();
			return;
		}
		if(key.equals("color"))
		{
			color = (Color) obj;
			panel.repaint();
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private double[] findPoint(Object obj) throws Exception
	{
		double[] arr = (double[]) findDoubleArray.t(obj);
		if(arr.length!=2) throw new Exception("Invalid double array size: "+arr.length);
		return arr;
	}

	private Color findColor(Object obj) throws Exception
	{return (Color) findColor.t(obj);}
	
	
	
	private void handlePoint(Graphics2D g2, int w, int h, Object key, Object value)
	{
		try
		{
			double[] point = findPoint(key); 
			Color c = findColor(value);
			
			int x = (int)(point[0]*w/dim[0]);
			int y = (int)(point[1]*h/dim[1]);
			int s = pointsize;
			int z = crosslength;
			
			g2.setColor(c);
			
			if(z==0)
			{
				g2.fillRect(x,y,s,s);
			}
			else
			{
				g2.fillRect(x-z,y,s+2*z,s);
				g2.fillRect(x,y-z,s,s+2*z);
			}
		}
		catch(Exception e)
		{Outside.err(this,"handlePoint(Graphics2D,int,int,Object,Object)",e);}
	}
	
	
	private class JPanel1 extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			int w = getWidth();
			int h = getHeight();
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(color);
			g2.fillRect(0,0,w,h);
			
			if(map!=null)
			{
				Iterator it = map.keySet().iterator();
				while(it.hasNext())
				{
					Object key = it.next();
					Object value = map.get(key);
					handlePoint(g2,w,h,key,value);
				}
			}
		}
	}
}
