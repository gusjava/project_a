package a.entity.gus06.sys.draw1.builder.image;

import a.framework.*;
import java.awt.Color;
import java.util.Map;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250826";}


	public static final String KEY_CENTER = "center";
	public static final String KEY_WIDTH = "width";
	public static final String KEY_HEIGHT = "height";
	public static final String KEY_IMAGE = "image";
	

	private Service findPoint2d;

	public EntityImpl() throws Exception
	{
		findPoint2d = Outside.service(this,"gus06.find.point2d");
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
		private Image image;
		
		public Draw(Map map) throws Exception
		{
			center = (Point2D) findPoint2d.t(get(map, KEY_CENTER));
			width = toDouble(get(map, KEY_WIDTH));
			height = toDouble(get(map, KEY_HEIGHT));
			image = (Image) get(map, KEY_IMAGE);
		}
		
		public void p(Object obj) throws Exception
		{
			Graphics2D g = (Graphics2D) obj;
			int x0 = (int) (center.getX()-width*0.5);
			int y0 = (int) (center.getY()-height*0.5);
			int w = (int) width;
			int h = (int) height;
			
			g.drawImage(image,x0,y0,w,h,null);
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