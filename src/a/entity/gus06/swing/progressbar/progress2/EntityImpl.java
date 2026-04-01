package a.entity.gus06.swing.progressbar.progress2;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.plaf.metal.MetalProgressBarUI;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

public class EntityImpl extends S1 implements Entity, I, R, V, E, F, P {

	public String creationDate() {return "20191127";}
	
	public final static Color COLOR_OVER = Color.GRAY;
	public final static Color COLOR_RUNNING = Color.LIGHT_GRAY;


	private Service toBool;

	private JProgressBar bar;
	private Color color0 = Color.RED;
	private boolean[] state;
	
	
	public EntityImpl() throws Exception
	{
		toBool = Outside.service(this,"gus06.find.boolean1");
		
		bar = new JProgressBar();
		bar.setBorderPainted(false);
		bar.setStringPainted(true);
		bar.setForeground(COLOR_RUNNING);
		bar.setMaximum(0);
		bar.setUI(new ProgressBarUI1());
	}
	
	
	public Object i() throws Exception
	{return bar;}
	
	

	public void e() throws Exception
	{increase();}
	
	
	public void p(Object obj) throws Exception
	{increase(obj);}


	public boolean f(Object obj) throws Exception
	{return value()<size() && value()>0;}
	
	

	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("size")){initSize(obj);return;}
		if(key.equals("reset")){reset();return;}
		if(key.equals("set")){set(obj);return;}
		if(key.equals("add")){add(obj);return;}
		if(key.equals("color")){initColor((Color) obj);return;}
		if(key.equals("color0")){initColor0((Color) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("size")) return Integer.valueOf(size());
		if(key.equals("current")) return Integer.valueOf(value());
		
		if(key.equals("keys")) return new String[]{"size","current"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void initSize(Object s)
	{
		int size = int_(s);
		bar.setMaximum(size);
		state = new boolean[size];
		for(int i=0;i<size;i++) state[i] = true;
		reset();
	}
	
	
	private void reset()
	{setValue(0);}
	
	
	private void set(Object s)
	{setValue(int_(s));}
	
	
	private void add(Object s)
	{setValue(value()+int_(s));}
	
	
	private void increase()
	{setValue(value()+1);}
	
	
	private void increase(Object obj) throws Exception
	{
		int v = value();
		state[v] = toBool.f(obj);
		setValue(v+1);
	}
	
	
	
	private void initColor(Color color)
	{bar.setForeground(color);}
	
	private void initColor0(Color color0)
	{this.color0 = color0;}
	
	
	
	private int int_(Object s)
	{return Integer.parseInt(""+s);}
	
		
	
	
	private void setValue(int v)
	{
		int size = size();
		if(v>size) v = size;
		else if(v<0) v = 0;
		bar.setValue(v);
		
		if(v==0) bar.setForeground(COLOR_RUNNING);
		else if(v==bar.getMaximum())  bar.setForeground(COLOR_OVER);
		
		modified();
	}
	
	
	private int value()
	{return bar.getValue();}
	
	
	private int size()
	{return bar.getMaximum();}
	
	
	private void modified()
	{send(this,"modified()");}
	
	
	
	
	
	private class ProgressBarUI1 extends MetalProgressBarUI
	{
		public void paintDeterminate(Graphics g, JComponent c)
		{
			super.paintDeterminate(g,c);
			int size = size();
			int value = value();
			
			if(size==0) return;
			
			Insets b = progressBar.getInsets();
			Rectangle r = progressBar.getBounds();
			
			int h = r.height;
			int w = r.width;
			
			int y0 = b.top;
			int y1 = h - b.bottom - b.top;
			
			int dx = (int)(w/size);
			if(dx==0) dx = 1;
			
			for(int i=0;i<value;i++) if(!state[i])
			{
				g.setColor(color0);
				int x0 = (int)(w*i/size) + b.left;
				g.fillRect(x0,y0,dx,y1);
			}
		}
	}
}
