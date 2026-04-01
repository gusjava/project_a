package a.entity.gus06.y.kanjivg1.anim.gui2;

import a.framework.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.parser.AWTPathProducer;
import org.apache.batik.parser.PathParser;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.PathIterator;
import java.awt.AlphaComposite;

public class EntityImpl implements Entity, I, P, V, R, E, ActionListener {

	public String creationDate() {return "20250731";}


	public static final int DEFAULT_SCALE = 2;
	public static final int DEFAULT_OFFSET = 10;
	public static final int DEFAULT_THICKNESS = 3;
	public static final Color DEFAULT_FOREGROUND = Color.BLACK;
	public static final Color DEFAULT_BACKGROUND = Color.WHITE;
	
	public static final int DEFAULT_TIMELAPSE = 30;
	public static final int DEFAULT_CYCLE1 = 60;
	public static final int DEFAULT_CYCLE2 = 40;

	private File file;
	private List strokes;
	private int count = 0;
	private Timer timer;
	private JPanel1 panel;
	
	private int scale = DEFAULT_SCALE;
	private int offset = DEFAULT_OFFSET;
	private int thickness = DEFAULT_THICKNESS;
	private Color foreground = DEFAULT_FOREGROUND;
	private Color background = DEFAULT_BACKGROUND;
	
	private int timelapse = DEFAULT_TIMELAPSE;
	private int cycle1 = DEFAULT_CYCLE1;
	private int cycle2 = DEFAULT_CYCLE2;

	private Service buildStrokes;
	
	
	public EntityImpl() throws Exception
	{
		buildStrokes = Outside.service(this,"gus06.y.kanjivg1.build.strokes");
		
		strokes = new ArrayList<>();
		panel = new JPanel1();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		reset();
		file = (File) obj;
		strokes = (List) buildStrokes.t(file);
	}
	
	
	public void e() throws Exception
	{
		reset();
		if(strokes.size()>0)
		{
			timer = new Timer(timelapse, this);
			timer.start();
		}
	}
	
	private void reset()
	{
		if(timer!=null && timer.isRunning()) timer.stop();
		count = 0;
		panel.repaint();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(strokes.isEmpty()) return;
		count++;
		panel.repaint();
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("scale")) {scale = (Integer) obj;return;}
		if(key.equals("offset")) {offset = (Integer) obj;return;}
		if(key.equals("thickness")) {thickness = (Integer) obj;return;}
		if(key.equals("foreground")) {foreground = (Color) obj;return;}
		if(key.equals("background")) {background = (Color) obj;return;}
		if(key.equals("timelapse")) {timelapse = (Integer) obj;return;}
		if(key.equals("cycle1")) {cycle1 = (Integer) obj;return;}
		if(key.equals("cycle2")) {cycle2 = (Integer) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("file")) return file;
		if(key.equals("strokes")) return strokes;
		if(key.equals("scale")) return scale;
		if(key.equals("offset")) return offset;
		if(key.equals("thickness")) return thickness;
		if(key.equals("foreground")) return foreground;
		if(key.equals("background")) return background;
		if(key.equals("timelapse")) return timelapse;
		if(key.equals("cycle1")) return cycle1;
		if(key.equals("cycle2")) return cycle2;
		
		if(key.equals("keys")) return new String[]{
			"file","strokes","scale","offset","thickness",
			"foreground","background","timelapse","cycle1","cycle2"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	private class JPanel1 extends JPanel
	{
		public JPanel1()
		{
			super();
			setOpaque(true);
		}
		
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g2.setColor(background);
			g2.fillRect(0,0,getWidth(),getHeight());
			
			g2.setStroke(new BasicStroke(thickness));
			g2.setColor(foreground);
			
			g2.translate(offset, offset);
			g2.scale(scale, scale);
			
			for (int i=0;i<strokes.size();i++)
			drawStroke(g2, i);
		}
	}
	
	private void drawStroke(Graphics2D g2, int index)
	{
		Shape stroke = (Shape) strokes.get(index);
		if(count>=index*cycle1+cycle2) {g2.draw(stroke);return;}
		
		if(count>=index*cycle1)
		{
			int delta = count - index*cycle1;
			float progress = (float) delta / (float) cycle2;
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, progress));
			g2.draw(stroke);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}
}