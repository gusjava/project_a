package a.entity.gus06.graphics.draw.string.finddimension1;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150617";}
	

	private Service setRenderingHints;
	
	
	public EntityImpl() throws Exception
	{setRenderingHints = Outside.service(this,"gus06.graphics.setrenderinghint.default1");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);

		String s = (String) t[0];
		Font font = (Font) t[1];
		
		return findDimension(s,font);
	}
	
	
	
	private int[] findDimension(String s, Font font) throws Exception
	{
		String[] lines = s.split("[\n\r]");
		
		Graphics2D g = img(1,1).createGraphics();
		setHints(g);
		
		g.setFont(font);

		FontMetrics fm = g.getFontMetrics();
		int h = fm.getHeight() * lines.length;

		int w = 0;
		for(String line:lines)
		{
			int lineWidth = lineWidth(fm,line);
			if(lineWidth > w) w = lineWidth;
		}
		g.dispose();
		return new int[]{w,h};
	}
	
	
	
	private void setHints(Graphics2D g) throws Exception
	{setRenderingHints.p(g);}

	
	
	
	private int lineWidth(FontMetrics fm, String s)
	{
		String[] p = s.split("\t");
		return partsWidth(fm,p) + tabsWidth(fm,p);
	}
	
	
	
	private int partsWidth(FontMetrics fm, String[] p)
	{
		int w = 0;
		for(String k:p) w += fm.stringWidth(k);
		return w;
	}
	
	
	private int tabsWidth(FontMetrics fm, String[] p)
	{
		int tabNb = p.length-1;
		return tabNb * tabWidth(fm);
	}
	
	
	private int tabWidth(FontMetrics fm)
	{return 2*fm.getHeight();}
	
	
	private BufferedImage img(int w, int h)
	{return new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);}
}
