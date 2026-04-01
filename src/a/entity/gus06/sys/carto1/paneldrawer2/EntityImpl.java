package a.entity.gus06.sys.carto1.paneldrawer2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import a.framework.*;

public class EntityImpl implements Entity, P, V, R {

	public String creationDate() {return "20161202";}

	
	public static final int EDGE = 10;
	public static final int MARGIN = 2;

	public static final Composite ALPHA1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f);
	public static final Composite ALPHA2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f);
	
	public static final Color BACKGROUND_PANEL = Color.WHITE;
	public static final Color BACKGROUND_LABEL = Color.LIGHT_GRAY;
	public static final Color FOREGROUND_LABEL = Color.BLACK;
	public static final Color COLOR_SELECTION = Color.GRAY;
	public static final Color COLOR_LINK = Color.BLACK;
	
	
	

	private Service drawArrow;
	private Map rectMap;
	private Map linksMap;
	private Map colorMap;
	
	private Set selectedSet;
	private Rectangle selectionArea;

	
	public EntityImpl() throws Exception
	{
		drawArrow = Outside.service(this,"*gus06.graphics.draw.point2d.arrow1");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("rectMap")) {rectMap = (Map) obj;return;}
		if(key.equals("linksMap")) {linksMap = (Map) obj;return;}
		if(key.equals("colorMap")) {colorMap = (Map) obj;return;}
		if(key.equals("selectedSet")) {selectedSet = (Set) obj;return;}
		if(key.equals("selectionArea")) {selectionArea = (Rectangle) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}



	public Object r(String key) throws Exception
	{
		if(key.equals("rectMap")) return rectMap;
		if(key.equals("linksMap")) return linksMap;
		if(key.equals("colorMap")) return colorMap;
		if(key.equals("selectedSet")) return selectedSet;
		if(key.equals("selectionArea")) return selectionArea;
		
		if(key.equals("keys"))
			return new String[]{"rectMap","linksMap","colorMap","selectedSet","selectionArea"};
		throw new Exception("Unknown key: "+key);
	}


	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Graphics2D g2 = (Graphics2D) o[0];
		JPanel panel = (JPanel) o[1];
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,	RenderingHints.VALUE_STROKE_PURE);
		
		g2.setPaint(BACKGROUND_PANEL);
		g2.fillRect(0,0,panel.getWidth(),panel.getHeight());
		
		if(rectMap!=null)
		{
			Iterator it1 = rectMap.keySet().iterator();
			while(it1.hasNext()) drawArrows(g2,(String) it1.next());
			
			Iterator it2 = rectMap.keySet().iterator();
			while(it2.hasNext()) drawLabel(g2,(String) it2.next());
		}
		if(selectionArea!=null)
		{
			g2.setPaint(COLOR_SELECTION);
			g2.draw(selectionArea);
		}
	}
	
	
	
	
	private void drawLabel(Graphics2D g2, String name)
	{
		Rectangle label = (Rectangle) rectMap.get(name);
		Color background = labelBackground(name);
		Color foreground = labelForeground(name);
		boolean selected = selectedSet.contains(name);
		
		Color bg = selected ? foreground : background;
		Color fg = selected ? background : foreground;
		
		g2.setPaint(bg);
		
		g2.fillRoundRect(label.x-MARGIN, 
				label.y-MARGIN, 
				label.width+2*MARGIN, 
				label.height+2*MARGIN, 
				EDGE, 
				EDGE);
		
		float x = (float) label.getX();
		float y = (float) label.getMaxY()-MARGIN;
		
		g2.setComposite(ALPHA1);
		g2.setPaint(fg);
		g2.drawString(name,x,y);
		g2.setComposite(ALPHA2);
	}
	
	
	
	private Color labelBackground(String name)
	{
		if(colorMap==null || !colorMap.containsKey(name)) return BACKGROUND_LABEL;
		return (Color) colorMap.get(name);
	}
	
	private Color labelForeground(String name)
	{
		return FOREGROUND_LABEL;
	}
	
	
	
	private void drawArrows(Graphics2D g2, String name)
	{
		try
		{
			if(!linksMap.containsKey(name)) return;
			
			Rectangle label = (Rectangle) rectMap.get(name);
			Set links = (Set) linksMap.get(name);
			
			g2.setPaint(COLOR_LINK);
			drawArrow.v("graphics2D",g2);
			
			Iterator it = links.iterator();
			while(it.hasNext())
			{
				String linkedName = (String) it.next();
				if(rectMap.containsKey(linkedName))
				{
					Rectangle linkedLabel = (Rectangle) rectMap.get(linkedName);
					drawArrow(label,linkedLabel);  
				}
			}
		}
		catch(Exception e)
		{Outside.err(this,"drawArrows(Graphics2D,String,Rectangle)",e);}
	}
	
	
	
	private void drawArrow(Rectangle start, Rectangle end) throws Exception
	{
		Point2D p1 = getCenter(start);
		Point2D p2 = getCenter(end);
		drawArrow.p(new Point2D[]{p1,p2});
	}

	private Point2D getCenter(Rectangle rect)
	{
		double x = (double)rect.x + (double)rect.width/2;
		double y = (double)rect.y + (double)rect.height/2;
		return new Point2D.Double(x,y);
	}
}
