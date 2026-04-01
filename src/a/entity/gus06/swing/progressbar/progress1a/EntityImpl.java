package a.entity.gus06.swing.progressbar.progress1a;

import a.framework.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;

public class EntityImpl extends S1 implements Entity, I, R, V, E, F {

	public String creationDate() {return "20141026";}
	
	public final static Color DEFAULT_COLOR_OVER = Color.GRAY;
	public final static Color DEFAULT_COLOR_RUNNING = Color.LIGHT_GRAY;


	private JProgressBar bar;
	private JLabel label;
	private JPanel panel;
	private Color colorOver = DEFAULT_COLOR_OVER;
	private Color colorRunning = DEFAULT_COLOR_RUNNING;
	
	public EntityImpl() throws Exception
	{
		bar = new JProgressBar();
		bar.setBorderPainted(false);
		bar.setStringPainted(true);
		bar.setForeground(colorRunning);
		bar.setMaximum(0);
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(bar,BorderLayout.CENTER);
		panel.add(label,BorderLayout.EAST);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	

	public void e() throws Exception
	{increase();}


	public boolean f(Object obj) throws Exception
	{return value()<size();}
	
	

	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("size")){initSize(obj);return;}
		if(key.equals("reset")){reset();return;}
		if(key.equals("set")){set(obj);return;}
		if(key.equals("add")){add(obj);return;}
		if(key.equals("color")){initColor((Color) obj);return;}
		if(key.equals("colorOver")){colorOver = (Color) obj;return;}
		if(key.equals("colorRunning")){colorRunning = (Color) obj;return;}
		if(key.equals("tooltip")){initTooltip((String) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("size")) return Integer.valueOf(size());
		if(key.equals("current")) return Integer.valueOf(value());
		if(key.equals("over")) return Boolean.valueOf(over());
		if(key.equals("foreground")) return foreground();
		
		if(key.equals("keys")) return new String[]{"size", "current", "over", "foreground"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void initSize(Object s)
	{
		bar.setMaximum(int_(s));
		reset();
	}
	
	private void initTooltip(String s)
	{
		bar.setToolTipText(s);
	}
	
	
	private void reset()
	{setValue(0);}
	
	
	private void set(Object s)
	{setValue(int_(s));}
	
	
	private void add(Object s)
	{setValue(value()+int_(s));}
	
	
	private void increase()
	{setValue(value()+1);}
	
	
	
	private void initColor(Color color)
	{
		bar.setBackground(color);
		label.setForeground(color);
	}
	
	
	
	private int int_(Object s)
	{return Integer.parseInt(""+s);}
	
	
	private void setValue(int v)
	{
		int size = size();
		if(v>size) v = size;
		else if(v<0) v = 0;
		
		bar.setValue(v);
		bar.setForeground(foreground());
		
		label.setText(" "+v+" / "+size+" ");
		modified();
	}
	
	private int value()
	{return bar.getValue();}
	
	private int size()
	{return bar.getMaximum();}
	
	private boolean over()
	{return value()==size();}
	
	private Color foreground()
	{return over() ? colorOver : colorRunning;}
	
	private void modified()
	{send(this,"modified()");}
}