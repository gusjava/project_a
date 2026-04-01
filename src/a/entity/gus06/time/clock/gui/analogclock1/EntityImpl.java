package a.entity.gus06.time.clock.gui.analogclock1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import a.framework.*;
import javax.swing.border.Border;


public class EntityImpl implements Entity, I, V, ActionListener {

	public String creationDate() {return "20160605";}

	
	private Dimension DIM = new Dimension(60,40);
	
	private Color screenColor = new Color(126,153,241);
	private Color frameColor = new Color(126,153,241);
	private Color needleColor = new Color(169,190,255);
	private Color numberColor = new Color(169,190,255);
	
	private double hour_f2 = 0.4;
	private double min_f2 = 0.65;
	private double second_f2 = 0.92;
	
	private double hour_f1 = -0.04;
	private double min_f1 = -0.04;
	private double second_f1 = -0.04;
	
	private double fontCoef = 0.25;
	
	
	
	
	private Service clock;
	private Service computeAngles;
	private Service buildOvalBorder;
	
	private JPanel0 panel;
	private double[] angles;
	private boolean failed = false;
	
	

	public EntityImpl() throws Exception
	{
		clock = Outside.service(this,"gus06.time.clock.holder");
		computeAngles = Outside.service(this,"gus06.time.clock.gui.analogclock1.needleangles");
		buildOvalBorder = Outside.service(this,"gus06.swing.border.build.ovalborder");
		
		angles = (double[]) computeAngles.g();
		
		panel = new JPanel0();
		panel.setBorder((Border) buildOvalBorder.g());
		
		panel.setPreferredSize(DIM);
		panel.setMinimumSize(DIM);
		panel.setMaximumSize(DIM);
		clock.addActionListener(this);
	}



	public Object i() throws Exception
	{return panel;}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("screenColor")) {screenColor = (Color) obj;return;}
		if(key.equals("frameColor")) {frameColor = (Color) obj;return;}
		if(key.equals("needleColor")) {needleColor = (Color) obj;return;}
		if(key.equals("numberColor")) {numberColor = (Color) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("second()")) updateClock();
	}
	
	
	
	private void updateClock()
	{
		if(failed) return;
		try
		{
			angles = (double[]) computeAngles.g();
			panel.repaint();
		}
		catch(Exception e)
		{
			Outside.err(this,"updateClock()",e);
			failed = true;
		}
	}
	
	
	
	
	private class JPanel0 extends JPanel
	{
		public void paintComponent(Graphics g)
		{	
			Graphics2D g2 = (Graphics2D)g;
			
			int w = getWidth();
			int h = getHeight();
			int d = Math.min(w,h);
			int r = d/2;
			int xc = w/2;
			int yc = h/2;
			
			
			//hours
			double hour_sin = Math.sin(angles[0]);
			double hour_cos = Math.cos(angles[0]);
			double hour_x1 = xc+r*hour_f1*hour_sin;
			double hour_y1 = yc-r*hour_f1*hour_cos;
			double hour_x2 = xc+r*hour_f2*hour_sin;
			double hour_y2 = yc-r*hour_f2*hour_cos;
			
			//minutes
			double min_sin = Math.sin(angles[1]);
			double min_cos = Math.cos(angles[1]);
			double min_x1 = xc+r*min_f1*min_sin;
			double min_y1 = yc-r*min_f1*min_cos;
			double min_x2 = xc+r*min_f2*min_sin;
			double min_y2 = yc-r*min_f2*min_cos;
			
			//secondes
			double second_sin = Math.sin(angles[2]);
			double second_cos = Math.cos(angles[2]);
			double second_x1 = xc+r*second_f1*second_sin;
			double second_y1 = yc-r*second_f1*second_cos;
			double second_x2 = xc+r*second_f2*second_sin;
			double second_y2 = yc-r*second_f2*second_cos;
			
			
			g2.setColor(screenColor);
			g2.fillRect(0,0,getWidth(),getHeight());
			
			g2.setColor(frameColor);
			g2.fillOval((int)(xc-r),(int)(yc-r),(int)d,(int)d);
			
			
			
			
			g2.setColor(numberColor);
			g2.setFont(g2.getFont().deriveFont((float)(r*fontCoef)));
			
			int _top = g2.getFontMetrics().getAscent();
			int _bottom = g2.getFontMetrics().getDescent();
			int _heigth = g2.getFontMetrics().getHeight();
			
			int w_12 = g2.getFontMetrics().stringWidth("12");
			g2.drawString("12",xc-w_12/2,yc-r+_top);
			
			int w_3 = g2.getFontMetrics().stringWidth("3");
			g2.drawString("3",w-2*w_3,yc+_bottom);
			
			int w_6 = g2.getFontMetrics().stringWidth("6");
			g2.drawString("6",xc-w_6/2,yc+r-_bottom);
			
			g2.drawString("9",w_3,yc+_bottom);
			
			
			
			
			g2.setColor(needleColor);
			
			g2.setStroke(new BasicStroke(r/20));
			g2.drawLine((int)hour_x1,(int)hour_y1,(int)hour_x2,(int)hour_y2);
			g2.drawLine((int)min_x1,(int)min_y1,(int)min_x2,(int)min_y2);
			
			g2.setStroke(new BasicStroke(r/30));
			g2.drawLine((int)second_x1,(int)second_y1,(int)second_x2,(int)second_y2);
		}
	}
	
	
	
	
	private void drawCenter(Graphics2D g2, int x, int y, String s)
	{
		int width = g2.getFontMetrics().stringWidth(s);
	}
}