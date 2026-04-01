package a.entity.gus06.sys.draw1.builder.text;

import a.framework.*;
import java.awt.Color;
import java.util.Map;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Font;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250826";}


	public static final String KEY_POINT = "point";
	public static final String KEY_COLOR = "color";
	public static final String KEY_FONT = "font";
	public static final String KEY_TEXT = "text";
	

	private Service findPoint2d;
	private Service findColor;
	private Service findFont;

	public EntityImpl() throws Exception
	{
		findPoint2d = Outside.service(this,"gus06.find.point2d");
		findColor = Outside.service(this,"gus06.find.color");
		findFont = Outside.service(this,"gus06.find.font");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Draw((Map) obj);
	}
	
	
	private class Draw implements P
	{
		private Point2D point;
		private Font font;
		private Color color;
		private String text;
		
		public Draw(Map map) throws Exception
		{
			point = (Point2D) findPoint2d.t(get(map, KEY_POINT));
			font = (Font) findFont.t(get(map, KEY_FONT));
			color = (Color) findColor.t(get0(map, KEY_COLOR));
			text = (String) get(map, KEY_TEXT);
		}
		
		public void p(Object obj) throws Exception
		{
			Graphics2D g = (Graphics2D) obj;
			
			int x = (int) point.getX();
			int y = (int) point.getY();
			
			g.setColor(color);
			g.setFont(font);
			g.drawString(text, x, y);
		}
	}
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	private Object get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
