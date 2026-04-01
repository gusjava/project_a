package a.entity.gus06.appli.gameoflife.gui.screen;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;


public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20150323";}

	public static final Color COLOR_ACTIVE = new Color(0,153,255);
	public static final Color COLOR_SLEEPING = new Color(153,204,255);
	public static final Color COLOR_EMPTY = Color.BLACK;
		
		
	private ScreenJPanel panel;
	
	private Color colorActive = COLOR_ACTIVE;
	private Color colorSleeping = COLOR_SLEEPING;
	private Color colorEmpty = COLOR_EMPTY;

	public EntityImpl() throws Exception
	{panel = new ScreenJPanel();}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		panel.setState((boolean[][])obj);
		panel.repaint();
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("colorActive")) {colorActive = (Color) obj;return;}
		if(key.equals("colorSleeping")) {colorSleeping = (Color) obj;return;}
		if(key.equals("colorEmpty")) {colorEmpty = (Color) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	


	public class ScreenJPanel extends JPanel
	{
		private boolean[][] state0;
		private boolean[][] state;
		
		private int x_state;
		private int y_state;
		
		private int x0;
		private int y0;
		private double c;
	
		public void setState(boolean[][] state)
		{
			this.state0 = this.state;
			this.state = state;
			
			if(this.state0==null)
			this.state0 = this.state;
			
			x_state = state.length;
			y_state = state[0].length;
		}
		
		public void paintComponent(Graphics g)
		{  
			super.paintComponent(g);
			if(state!=null) paintState(g);
		}
		
		private void paintState(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			
			double h = ((double)getHeight()) / ((double)x_state);
			double w = ((double)getWidth()) / ((double)y_state);
			
			if(h>w)
			{
				c = w;
				x0 = (int) ((getHeight()-(x_state*c))*0.5);
				y0 = 0;
			}
			else
			{
				c = h;
				x0 = 0;
				y0 = (int) ((getWidth()-(y_state*c))*0.5);
			}
			
			g2.setColor(colorEmpty);
			g2.fillRect(y0,x0,(int)(y_state*c),(int)(x_state*c));
			
			for(int i=0;i<x_state;i++)
			for(int j=0;j<y_state;j++)
			{if(state[i][j]) fillCell(g2,i,j);}
		}
		
		private void fillCell(Graphics2D g2, int i, int j)
		{
			g2.setColor(getColor(i,j));
			g2.fillRect(y0+(int)(c*j),x0+(int)(c*i),(int)c+1,(int)c+1);
		}
		
		
		private Color getColor(int i, int j)
		{
			return state[i][j] ^ state0[i][j] ? colorActive : colorSleeping;
		}
	}
}