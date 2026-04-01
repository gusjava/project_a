package a.entity.gus06.awt.bufferedimage.build.fromproperties1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150616";}


	public static final Font DEFAULT_FONT = new Font("Arial",Font.PLAIN,20);
	public static final int DEFAULT_MARGIN = 15;
	


	private Service drawText;
	private Service findDimension;
	
	private Font font = DEFAULT_FONT;
	private int margin = DEFAULT_MARGIN;
	
	private int height = 0;
	private int width = 0;
	
	
	
	private Font font_key;
	private int width_key;
	private int x_key;
	
	private Font font_value;
	private int width_value;
	private int x_value;
	
	private int y;
	
	
	public EntityImpl() throws Exception
	{
		drawText = Outside.service(this,"gus06.graphics.draw.string.drawstring1");
		findDimension = Outside.service(this,"gus06.graphics.draw.string.finddimension1");
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("font")) {font = (Font) obj;return;}
		if(key.equals("margin")) {margin = Integer.parseInt((String) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	

	public Object t(Object obj) throws Exception
	{
		Map prop = (Map) obj;
		
		font_key = font.deriveFont(Font.BOLD);
		font_value = font.deriveFont(Font.PLAIN);
		
		computeDim(prop);
		
		BufferedImage img = img(width,height);
		Graphics2D g = img.createGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,width,height);
		g.setColor(Color.BLACK);
		
		drawProp(g,prop);
		g.dispose();
		
		return img;
	}
	
	
	
	
	private void computeDim(Map prop) throws Exception
	{
		height = 0;
		width_key = 0;
		width_value = 0;
		
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) prop.get(key);
			
			int[] dim_key = findDim(key+":",font_key);
			int[] dim_value = findDim(value,font_value);
			
			width_key = Math.max(width_key,dim_key[0]);
			width_value = Math.max(width_value,dim_value[0]);
			height += Math.max(dim_key[1],dim_value[1]);
		}
		
		width = width_key + width_value + 3 * margin;
		height += 2 * margin;
	}
	
	
	
	
	private void drawProp(Graphics2D g, Map prop) throws Exception
	{
		y = margin;
		x_key = margin;
		x_value = width_key + 2 * margin;
				
		ArrayList keys = new ArrayList(prop.keySet());
		Collections.sort(keys);
		
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			String value = (String) prop.get(key);
			
			drawKeyValue(g,key,value);
		}
	}
	
	

	
	private void drawKeyValue(Graphics2D g, String key, String value) throws Exception
	{
		g.setFont(font_key);
		drawText(g,x_key,y,key+":");
		
		g.setFont(font_value);
		drawText(g,x_value,y,value);
		
		int[] dim_key = findDim(key+":",font_key);
		int[] dim_value = findDim(value,font_value);
		
		y += Math.max(dim_key[1],dim_value[1]);
	}
	
	
	
	
	private int[] findDim(String text, Font font) throws Exception
	{return (int[]) findDimension.t(new Object[]{text,font});}
	
	
	private void drawText(Graphics2D g, int x, int y, String text) throws Exception
	{drawText.p(new Object[]{g,new Point(x,y),text});}
	
	
	private BufferedImage img(int w, int h)
	{return new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);}
}
