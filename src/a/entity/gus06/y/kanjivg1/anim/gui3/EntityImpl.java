package a.entity.gus06.y.kanjivg1.anim.gui3;

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
import java.awt.geom.Rectangle2D;
import java.awt.Insets;

public class EntityImpl implements Entity, I, P, V, R, E, ActionListener {

	public String creationDate() {return "20250802";}


	public static final int DEFAULT_MARGIN = 10;
	public static final int DEFAULT_THICKNESS = 3;
	public static final Color DEFAULT_FOREGROUND = Color.BLACK;
	public static final Color DEFAULT_BACKGROUND = Color.WHITE;
	
	public static final int DEFAULT_TIMELAPSE = 30;
	public static final int DEFAULT_CYCLE1 = 60;
	public static final int DEFAULT_CYCLE2 = 40;
	
	
	private Timer timer;
	private JPanel1 panel;

	private Object source;
	private List strokes;
	private Rectangle2D bounds;
	private int count;
	private boolean interrupted;
	
	private int margin = DEFAULT_MARGIN;
	private int thickness = DEFAULT_THICKNESS;
	private Color foreground = DEFAULT_FOREGROUND;
	private Color background = DEFAULT_BACKGROUND;
	
	private int timelapse = DEFAULT_TIMELAPSE;
	private int cycle1 = DEFAULT_CYCLE1;
	private int cycle2 = DEFAULT_CYCLE2;

	private Service buildStrokes;
	private Service drawStrokeProgress;
	
	
	public EntityImpl() throws Exception
	{
		buildStrokes = Outside.service(this,"gus06.y.kanjivg1.build.strokes");
		drawStrokeProgress = Outside.service(this,"gus06.y.kanjivg1.draw.stroke.progress");
		
		strokes = new ArrayList<>();
		panel = new JPanel1();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		reset();
		source = obj;
		strokes = (List) buildStrokes.t(source);
		bounds = getKanjiBounds();
	}
	
	
	public void e() throws Exception
	{start();}
	
	
	private void start()
	{
		reset();
		if(strokes.isEmpty()) return;
		timer = new Timer(timelapse, this);
		timer.start();
	}
	
	private void stop()
	{
		if(timer!=null && timer.isRunning()) timer.stop();
		interrupted = false;
	}
	
	private void reset()
	{
		stop();
		setCount(0);
	}
	
	private void setCount(int count)
	{
		this.count = count;
		panel.repaint();
	}
	
	
	private void setStroke(int nb)
	{setCount(nb*cycle1);}
	
	
	private int stroke()
	{return count/cycle1;}
	
	
	private int total()
	{return strokes.size();}
	
	
	private String progress()
	{return stroke()+"/"+total();}
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(interrupted) return;
		setCount(count+1);
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("margin")) {margin = (Integer) obj;return;}
		if(key.equals("thickness")) {thickness = (Integer) obj;return;}
		if(key.equals("foreground")) {foreground = (Color) obj;return;}
		if(key.equals("background")) {background = (Color) obj;return;}
		if(key.equals("timelapse")) {timelapse = (Integer) obj;return;}
		if(key.equals("cycle1")) {cycle1 = (Integer) obj;return;}
		if(key.equals("cycle2")) {cycle2 = (Integer) obj;return;}
		if(key.equals("interrupted")) {interrupted = (Boolean) obj;return;}
		if(key.equals("count")) {setCount((Integer) obj);return;}
		if(key.equals("stroke")) {setStroke((Integer) obj);return;}
		if(key.equals("start")) {start();return;}
		if(key.equals("stop")) {stop();return;}
		if(key.equals("reset")) {reset();return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("source")) return source;
		if(key.equals("strokes")) return strokes;
		if(key.equals("margin")) return margin;
		if(key.equals("thickness")) return thickness;
		if(key.equals("foreground")) return foreground;
		if(key.equals("background")) return background;
		if(key.equals("timelapse")) return timelapse;
		if(key.equals("cycle1")) return cycle1;
		if(key.equals("cycle2")) return cycle2;
		if(key.equals("interrupted")) return interrupted;
		if(key.equals("count")) return count;
		if(key.equals("stroke")) return stroke();
		if(key.equals("total")) return total();
		if(key.equals("progress")) return progress();
		
		if(key.equals("keys")) return new String[]{
			"source","strokes","margin","thickness",
			"foreground","background","timelapse","cycle1","cycle2",
			"interrupted","count","stroke","total","progress"};
		
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
			
			g2.setColor(foreground);
			g2.setStroke(new BasicStroke(thickness));
			
			double panelW = getWidth();
			double panelH = getHeight();
			
			double boundsW = bounds!=null ? bounds.getWidth() : 0;
			double boundsH = bounds!=null ? bounds.getHeight() : 0;
			
			Insets ins = getInsets();
			
			double cx = (double)(panelW-2*margin-ins.left-ins.right)/boundsW;
    			double cy = (double)(panelH-2*margin-ins.bottom-ins.top)/boundsH;
			
			if(cx>=cy)
			{
				int a = (int) ((panelW-2*margin-ins.left-ins.right-boundsW*cy)/2.0);
				g2.translate(a+margin, margin);
				g2.scale(cy, cy);
				g2.translate(-bounds.getX(), -bounds.getY());
			}
			else
			{
				int a = (int) ((panelH-2*margin-ins.bottom-ins.top-boundsH*cx)/2.0);
				g2.translate(margin, a+margin);
				g2.scale(cx, cx);
				g2.translate(-bounds.getX(), -bounds.getY());
			}
			
			for (int i=0;i<strokes.size();i++)
			drawStroke(g2, i);
		}
	}
	
	private void drawStroke(Graphics2D g2, int index)
	{
		try
		{
			Shape stroke = (Shape) strokes.get(index);
			if(count>=index*cycle1+cycle2) {g2.draw(stroke);return;}
			
			if(count>=index*cycle1)
			{
				int delta = count - index*cycle1;
				float progress = (float) delta / (float) cycle2;
				drawStrokeProgress.p(new Object[]{g2,stroke,progress});
			}
		}
		catch(Exception e)
		{Outside.err(this,"drawStroke(Graphics2D, int)",e);}
	}
	
	private Rectangle2D getKanjiBounds()
	{
		Rectangle2D bounds = null;
		for (int i=0;i<strokes.size();i++)
		{
			Shape stroke = (Shape) strokes.get(i);
			Rectangle2D strokeBounds = stroke.getBounds2D();
			bounds = bounds!=null ? bounds.createUnion(strokeBounds) : strokeBounds;
		}
		return bounds;
	}
}
