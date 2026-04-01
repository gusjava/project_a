/*
 * Framework gus04
 * 
 * Copyright (C) 2005 (Augustin Delale)
 * Ce programme est libre, vous pouvez le redistribuer et/ou le modifier selon les termes
 * de la Licence Publique Generale GNU publiee par la Free Software Foundation (version 2
 * ou bien toute autre version ulterieure choisie par vous).
 * 
 * Ce programme est distribue car potentiellement utile, mais SANS AUCUNE GARANTIE, 
 * ni explicite ni implicite, y compris les garanties de commercialisation ou d'adaptation
 * dans un but specifique. Reportez-vous e la Licence Publique Generale GNU pour plus de details.
 * 
 * Vous devez avoir reeu une copie de la Licence Publique Generale GNU en meme temps que ce programme ;
 * si ce n'est pas le cas, ecrivez e la Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, etats-Unis. 
 */
package a.entity.gus06.swing.panel.screen.realtime1;


import a.framework.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;


class ScreenJPanel extends JPanel {

	
	
	private Color screenColor = Color.WHITE;
	private Color functionColor = Color.BLACK;
	private Color axisColor;
	
	private long lapse = 100;
	private double y_min;
	private double y_max;
	
	
	public void setScreenColor(Color screenColor)
	{this.screenColor = screenColor;}
	
	public void setFunctionColor(Color functionColor)
	{this.functionColor = functionColor;}

	public void setAxisColor(Color axisColor)
	{this.axisColor = axisColor;}
	
	public void setLapse(long lapse)
	{this.lapse = lapse;}
	
	public void setRange(double[] range) throws Exception
	{
		if(range.length!=2)
			throw new Exception("Unvalid range size: "+range.length);
		y_min = range[0];
		y_max = range[1];
	}
	
	

	private H f;
	private Timer timer;
	private TimerTask task;
	private ValueJLabel label;
	private Vector records;
	
	
	
	public JLabel getValueLabel()
	{return label;}
	
	public Vector getRecords()
	{return records;}
	
	private void recordValue(double value)
	{
		records.add(Double.valueOf(value));
	}
	
	
	
	
	
	public ScreenJPanel(Timer timer)
	{
		this.timer = timer;
		records = new Vector();
		label = new ValueJLabel();
		
		y_min = Double.MAX_VALUE;
		y_max = Double.MIN_VALUE;
	}
	

	
	
	
	
	
	public void initFunction(H f)
	{ 
		this.f = f;
		task = new TimerTask(){
			public void run()
			{if(!recording) recordValues();}};
		
		timer.schedule(task, new Date(),lapse);
	}
	
	
	
	
	
	
	
	
	
	private boolean recording = false;

	private synchronized void recordValues()
	{
		recording = true;
		try
		{
			double v = f.h(0);
			y_min = Math.min(y_min,v-1);
			y_max = Math.max(y_max,v+1);
			
			recordValue(v);
			label.updateDisplay(""+v);
			repaint();
		}
		catch(Exception e)
		{
			recordValue(-1);
			label.updateDisplay(e.toString());
			repaint();
		}
		recording = false;
	}




	
	
	
	
	public void paintComponent(Graphics g)
	{
		//System.out.println("paintComponent");
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(screenColor);
		g2.fillRect(0,0,getWidth(),getHeight());
		if(axisColor!=null) drawAxis(g2);
		drawFunction(g2);
	}

	
	
	
	private void drawFunction(Graphics2D g2)
	{
		g2.setColor(functionColor);
		
		int Ymem = -1;
		int end = records.size();
		int start = Math.max(0,end-getWidth());
		
		for(int i=start;i<end;i++)
		{
			Double v = (Double)records.get(i);
			double y = v.doubleValue();
			
			if(y<y_max && y>y_min)
			{
				int Y = (int)((y_max-y)/(y_max-y_min)*getHeight());
				if(Ymem!=-1) g2.drawLine(i-1-start,Ymem,i-start,Y);
				Ymem = Y;
			}
			else Ymem = -1;
		}
	}
	
	
	
	
	
	
	private void drawAxis(Graphics2D g2)
	{
		if(y_min>0 || y_max<0)return;
		g2.setColor(axisColor);
		int Y = (int)(y_max/(y_max-y_min)*getHeight());
		g2.drawLine(0,Y,getWidth(),Y);
	}

	
	
	
	
	
	
	private class ValueJLabel extends JLabel
	{
		private String text="";
		private String value="";
		
		public void setText(String text)
		{
			this.text = text;
			super.setText(text+value);
		}
		public void updateDisplay(String value)
		{
			this.value = value;
			super.setText(text+value);
		}
	}
}
