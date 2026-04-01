package a.entity.gus06.sys.draw2.builder.rect;

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
	private Service autoUpdate;

	public EntityImpl() throws Exception
	{
		findPoint2d = Outside.service(this,"gus06.find.point2d");
		findColor = Outside.service(this,"gus06.find.color");
		autoUpdate = Outside.service(this,"gus06.map.auto.update");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Draw((Map) obj);
	}
	
	
	private class Draw implements P
	{
		private Map map;
		
		public Draw(Map map) throws Exception
		{this.map = map;}
		
		public void p(Object obj) throws Exception
		{
			Graphics2D g = (Graphics2D) obj;
			
			map = (Map) autoUpdate.t(map);
			
			Point2D center = (Point2D) findPoint2d.t(get(map, KEY_CENTER));
			double width = toDouble(get(map, KEY_WIDTH));
			double height = toDouble(get(map, KEY_HEIGHT));
			Color foreground = (Color) findColor.t(get0(map, KEY_FOREGROUND));
			Color background = (Color) findColor.t(get0(map, KEY_BACKGROUND));
			
			int x0 = (int) (center.getX()-width*0.5);
			int y0 = (int) (center.getY()-height*0.5);
			int w = (int) width;
			int h = (int) height;
			
			if(background!=null)
			{
				g.setColor(background);
				g.fillRect(x0, y0, w, h);
			}
			if(foreground!=null)
			{
				g.setColor(foreground);
				g.drawRect(x0, y0, w, h);
			}
		}
	}
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return retrieve(map.get(key));
	}
	
	private Object get0(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return retrieve(map.get(key));
	}
	
	private Object retrieve(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof G) return ((G) obj).g();
		return obj;
	}
	
	private double toDouble(Object obj)
	{
		return Double.parseDouble(""+obj);
	}
}