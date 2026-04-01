package a.entity.gus06.sys.draw1.builder.oval;

import a.framework.*;
import java.awt.Color;
import java.util.Map;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250826";}


	public static final String KEY_CENTER = "center";
	public static final String KEY_WIDTH = "width";
	public static final String KEY_HEIGHT = "height";
	public static final String KEY_BACKGROUND = "background";
	public static final String KEY_FOREGROUND = "foreground";
	

	private Service findPoint2d;
	private Service findColor;

	public EntityImpl() throws Exception
	{
		findPoint2d = Outside.service(this,"gus06.find.point2d");
		findColor = Outside.service(this,"gus06.find.color");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Draw((Map) obj);
	}
	
	
	private class Draw implements P
	{
		private Point2D center;
		private double width;
		private double height;
		private Color foreground;
		private Color background;
		
		public Draw(Map map) throws Exception
		{
			center = (Point2D) findPoint2d.t(get(map, KEY_CENTER));
			width = toDouble(get(map, KEY_WIDTH));
			height = toDouble(get(map, KEY_HEIGHT));
			foreground = (Color) findColor.t(get0(map, KEY_FOREGROUND));
			background = (Color) findColor.t(get0(map, KEY_BACKGROUND));
		}
		
		public void p(Object obj) throws Exception
		{
			Graphics2D g = (Graphics2D) obj;
			int x0 = (int) (center.getX()-width*0.5);
			int y0 = (int) (center.getY()-height*0.5);
			int w = (int) width;
			int h = (int) height;
			
			if(background!=null)
			{
				g.setColor(background);
				g.fillOval(x0, y0, w, h);
			}
			if(foreground!=null)
			{
				g.setColor(foreground);
				g.drawOval(x0, y0, w, h);
			}
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
	
	private double toDouble(Object obj)
	{
		return Double.parseDouble(""+obj);
	}
}