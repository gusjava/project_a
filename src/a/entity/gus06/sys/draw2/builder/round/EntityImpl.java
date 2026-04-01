package a.entity.gus06.sys.draw2.builder.round;

import a.framework.*;
import java.awt.Color;
import java.util.Map;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250826";}


	public static final String KEY_CENTER = "center";
	public static final String KEY_RADIUS = "radius";
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
			double radius = toDouble(get(map, KEY_RADIUS));
			Color foreground = (Color) findColor.t(get0(map, KEY_FOREGROUND));
			Color background = (Color) findColor.t(get0(map, KEY_BACKGROUND));
			
			int x0 = (int) (center.getX()-radius);
			int y0 = (int) (center.getY()-radius);
			int w = (int) (radius*2);
			int h = (int) (radius*2);
			
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