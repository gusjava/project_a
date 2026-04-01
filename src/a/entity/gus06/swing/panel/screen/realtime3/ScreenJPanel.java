package a.entity.gus06.swing.panel.screen.realtime3;


import a.framework.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;


class ScreenJPanel extends JPanel implements MouseMotionListener {

	public static final String TYPE_NORMAL = "TYPE_NORMAL";
	public static final String TYPE_SHRINKED = "TYPE_SHRINKED";
	public static final String TYPE_SLIPPING = "TYPE_SLIPPING";
	
	
	private Robot robot;
	
	private Color axisColor;
	
	private long lapse = 100;
	private double y_min;
	private double y_max;
	private String type = TYPE_NORMAL;
	
	private Timer timer;
	private TimerTask task;
	private boolean recording = false;
	

	private List list_functions;
	private List list_colors;
	private List list_names;
	private List list_labels;
	
	private List records;
	private JPanel panelInfo;
	
	
	
	
	
	public ScreenJPanel(Timer timer) throws Exception
	{
		super();
		this.timer = timer;
		robot = new Robot();
		
		list_functions = new ArrayList();
		list_colors = new ArrayList();
		list_names = new ArrayList();
		list_labels = new ArrayList();
		
		records = new ArrayList();
		
		panelInfo = new JPanel(new GridLayout(1,1));
		
		y_min = Double.MAX_VALUE;
		y_max = Double.MIN_VALUE;
		
		setFocusable(true);
		addMouseMotionListener(this);
	}
	
	
	
	
	public void setAxisColor(Color axisColor)
	{
		this.axisColor = axisColor;
		repaint();
	}
	
	public void setLapse(long lapse)
	{
		this.lapse = lapse;
		initTask();
	}
	
	public void setRange(double[] range) throws Exception
	{
		if(range.length!=2)
			throw new Exception("Unvalid range size: "+range.length);
		y_min = range[0];
		y_max = range[1];
		repaint();
	}
	
	
	public void setType(String type)
	{
		this.type = type;
		repaint();
	}
	
	
	public JPanel getPanelInfo()
	{return panelInfo;}
	
	public List getRecords()
	{return records;}
	
	
	

	
	public synchronized void startRecording()
	{
		recording = true;
		if(task==null) initTask();
	}
	
	
	public synchronized void stopRecording()
	{
		recording = false;
	}
	
	
	private void initTask()
	{
		if(task!=null) task.cancel();
		task = new TimerTask(){
			public void run(){recordValues();}
		};
		timer.schedule(task,new Date(),lapse);
	}
	
	
	
	
	
	public synchronized void addFunction(String n, Color c, H f)
	{ 
		list_functions.add(f);
		list_colors.add(c);
		list_names.add(n);
	}
	
	
	
	
	public void mouseDragged(MouseEvent evt) {}
	public void mouseMoved(MouseEvent evt)
	{
		Point p = evt.getLocationOnScreen();
		Color c = robot.getPixelColor(p.x, p.y);
		int rgb = c.getRGB();
		
		//double value = y_max - p.y*(y_max-y_min)/getHeight();
		int index = findColorIndex(rgb);
		
//		if(index==-1) setToolTipText(""+value);
//		else setToolTipText(list_names.get(index)+": "+value);
		
		if(index==-1) setToolTipText(null);
		else setToolTipText((String)list_names.get(index));
	}
	
	private int findColorIndex(int rgb)
	{
		for(int i=0;i<list_colors.size();i++)
		{
			Color c = (Color) list_colors.get(i);
			if(c.getRGB()==rgb) return i;
		}
		return -1;
	}
	
	
	
	
	
	
	

	private synchronized void recordValues()
	{
		if(!recording) return;
		double[] values = new double[list_functions.size()];
		for(int i=0;i<list_functions.size();i++)
		{
			H f = (H) list_functions.get(i);
			values[i] = value(f);
			y_min = Math.min(y_min,values[i]-1);
			y_max = Math.max(y_max,values[i]+1);
		}
		records.add(values);
		updatePanelInfo(values);
		repaint();
	}

	

	private double value(H f)
	{
		try{return f.h(0);}
		catch(Exception e){return -1;}
	}

	
	
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		if(axisColor!=null) drawAxis(g2);
		
		if(type.equals(TYPE_NORMAL))
			drawFunctions_normal(g2);
		else if(type.equals(TYPE_SHRINKED))
			drawFunctions_shrinked(g2);
		else if(type.equals(TYPE_SLIPPING))
			drawFunctions_slipping(g2);
		
	}

	
	
	
	private void drawFunctions_normal(Graphics2D g2)
	{
		int number = records.size();
		
		for(int i=0;i<number;i++)
			drawValues(g2,i,i);
		
//		Dimension dim = new Dimension(number,getHeight());
//		setPreferredSize(dim);
//		setSize(dim);
	}
	
	
	
	private void drawFunctions_shrinked(Graphics2D g2)
	{
		int number = records.size();
		
		if(number<=getWidth())
		for(int i=0;i<number;i++)
			drawValues(g2,i,i);
		else
		for(int i=0;i<getWidth();i++)
		{
			int k = (int)(i*number/getWidth());
			drawValues(g2,k,k);
		}
	}
	
	
	
	private void drawFunctions_slipping(Graphics2D g2)
	{
		int number = records.size();
		
		if(number<=getWidth())
		for(int i=0;i<number;i++)
			drawValues(g2,i,i);
		else
		{
			int d = number-getWidth();
			for(int i=0;i<getWidth();i++)
				drawValues(g2,i+d,i);
		}
	}
	
	
	
	
	
	
	private void drawValues(Graphics2D g2, int index, int x)
	{
		if(index==0) return;
		double[] values0 = (double[])records.get(index-1);
		double[] values = (double[])records.get(index);
		
		for(int i=0;i<values.length;i++)
		{
			double value0 = values0[i];
			double value = values[i];
			
			Color color = (Color) list_colors.get(i);
			g2.setColor(color);
			
			if(value<y_max && value>y_min)
			{
				int Y0 = (int)((y_max-value0)/(y_max-y_min)*getHeight());
				int Y = (int)((y_max-value)/(y_max-y_min)*getHeight());
				g2.drawLine(x-1,Y0,x,Y);
			}
		}
	}
	
	
	
	
	
	
	private void drawAxis(Graphics2D g2)
	{
		if(y_min>0 || y_max<0)return;
		g2.setColor(axisColor);
		int Y = (int)(y_max/(y_max-y_min)*getHeight());
		g2.drawLine(0,Y,getWidth(),Y);
	}

	
	
	
	
	
	private void updatePanelInfo(double[] values)
	{
		int n = values.length;
		if(list_labels.size()!=n)
		{
			panelInfo.removeAll();
			panelInfo.setLayout(new GridLayout(n,1));
			while(list_labels.size()<n) list_labels.add(new JLabel());
			for(int i=0;i<n;i++) panelInfo.add((JLabel)list_labels.get(i));
		}
		for(int i=0;i<n;i++)
		{
			JLabel label = (JLabel)list_labels.get(i);
			Color color = (Color)list_colors.get(i);
			String name = (String)list_names.get(i);
			
			label.setForeground(color);
			label.setText(" "+name+": "+values[i]);
			label.setToolTipText(name+": "+values[i]);
		}
	}
}
