package a.entity.gus06.graphics.draw.string.drawstring1;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150617";}

	
	private Service setRenderingHints;
	
	
	public EntityImpl() throws Exception
	{setRenderingHints = Outside.service(this,"gus06.graphics.setrenderinghint.default1");}
	


	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=3) throw new Exception("Wrong data number: "+t.length);
		
		Graphics2D g = (Graphics2D) t[0];
		Point p = (Point) t[1];
		String s = (String) t[2];
		
		setHints(g);
		drawText(g,s,p);
	}
	
	
	
	private void setHints(Graphics2D g) throws Exception
	{setRenderingHints.p(g);}
	
	
	
	
	
	private void drawText(Graphics2D g, String s, Point p)
	{
		String[] lines = s.split("[\n\r]");
		
		FontMetrics fm = g.getFontMetrics();
		for(int i=0;i<lines.length;i++)
		{
			int x = p.x;
			int y = p.y + fm.getAscent() + fm.getHeight() * i;
			drawLine(g,lines[i],x,y);
		}
	}
	
	
	
	
	private void drawLine(Graphics2D g, String s, int x, int y)
	{
		FontMetrics fm = g.getFontMetrics();
		int tabWidth = tabWidth(fm);
		for(String part:s.split("\t"))
		{
			g.drawString(part,x,y);
			x += fm.stringWidth(part) + tabWidth;
		}
	}
	

	
	private int tabWidth(FontMetrics fm)
	{return 2*fm.getHeight();}
}
