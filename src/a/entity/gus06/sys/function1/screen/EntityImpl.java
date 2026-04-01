package a.entity.gus06.sys.function1.screen;

import a.framework.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class EntityImpl implements Entity, P, V, I {

	public String creationDate() {return "20160612";}

	
	private Color screenColor = Color.WHITE;
	private Color axisColor = new Color(234,234,234);
	private double[] def = new double[]{-20,20,-5,5};
	
	
	private Color color = Color.BLACK;
	
	private ScreenJPanel screen;
	
	
	public EntityImpl()
	{screen = new ScreenJPanel();}
	
	
	public Object i() throws Exception
	{return screen;}
	
	
	public void p(Object obj) throws Exception
	{
		screen.addFunction((H) obj, color);
		screen.repaint();
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("color"))
		{
			color = (Color) obj;
			return;
		}
		if(key.equals("screenColor"))
		{
			screenColor = (Color) obj;
			screen.repaint();
			return;
		}
		if(key.equals("axisColor"))
		{
			axisColor = (Color) obj;
			screen.repaint();
			return;
		}
		if(key.equals("definition"))
		{
			def = (double[])obj;
			if(def.length!=4) throw new Exception("invalid definition number: "+def.length);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private class ScreenJPanel extends JPanel
	{
		private Vector functions;
		private Vector colors;
		
		public ScreenJPanel()
		{
			super();
			functions = new Vector();
			colors = new Vector();
		}
		
		public void addFunction(H function, Color color)
		{
			functions.add(function);
			colors.add(color);
		}
		
		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			
			g2.setColor(screenColor);
			g2.fillRect(0,0,getWidth(),getHeight());
			
			g2.setColor(axisColor);
			g2.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
			g2.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
			
			for(int i=0;i<functions.size();i++)
			drawFunction(g2,i);
		}
		
		private void drawFunction(Graphics2D g2, int index)
		{
			Color color = (Color) colors.get(index);
			H function = (H) functions.get(index);
			
			double x_min = def[0];
			double x_max = def[1];
			double y_min = def[2];
			double y_max = def[3];
			
			g2.setColor(color);
			int Ymem = -1;
			
			for(int i=0;i<getWidth();i++)
			{
				try
				{
					double x = x_min + i*(x_max-x_min)/getWidth();
					double y = function.h(x);
					
					if(y<y_max && y>y_min)
					{
						int Y = (int)((y_max-y)/(y_max-y_min)*getHeight());
						
						if(Ymem!=-1) g2.drawLine(i-1,Ymem,i,Y);
						Ymem = Y;
					}
					else Ymem = -1;
				}
				catch(Exception e)
				{Ymem = -1;}
			}
		}
	}


}
