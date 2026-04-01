package a.entity.gus06.swing.panel.screen.realtime2;


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
	
	
	
	private Timer timer;
	private TimerTask task;
	
	

	private H f;
	private Color functionColor;
	private ValueJLabel label;
	private Vector records;
	
	
	
	
	public JLabel getValueLabel()
	{return label;}
	
	public Vector getRecords()
	{return records;}
	
	
	
	
	
	
	
	public ScreenJPanel(Timer timer)
	{
		super();
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
			{recordValues();}};
		
		timer.schedule(task, new Date(),lapse);
	}
	
	
	
	
	
	private double value()
	{
		try{return f.h(0);}
		catch(Exception e){return -1;}
	}
	
	
	
	
	

	private void recordValues()
	{
		double v = value();
		y_min = Math.min(y_min,v-1);
		y_max = Math.max(y_max,v+1);
		
		records.add(Double.valueOf(v));
		label.updateDisplay(v);
		repaint();
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
		int s = records.size();
		if(s<=getWidth())
		for(int i=0;i<s;i++)
		{
			Double v = (Double)records.get(i);
			double y = v.doubleValue();
			
			if(y<y_max && y>y_min)
			{
				int Y = (int)((y_max-y)/(y_max-y_min)*getHeight());
				if(Ymem!=-1) g2.drawLine(i-1,Ymem,i,Y);
				Ymem = Y;
			}
			else Ymem = -1;
		}
		else
		for(int i=0;i<getWidth();i++)
		{
			int k = (int)(i*s/getWidth());
			Double v = (Double)records.get(k);
			double y = v.doubleValue();
			
			if(y<y_max && y>y_min)
			{
				int Y = (int)((y_max-y)/(y_max-y_min)*getHeight());
				if(Ymem!=-1) g2.drawLine(i-1,Ymem,i,Y);
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
		private double value = 0;
		
		public void setText(String text)
		{
			this.text = text;
			super.setText(text+value);
		}
		public void updateDisplay(double value)
		{
			this.value = value;
			super.setText(text+value);
		}
	}
}
