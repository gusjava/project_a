package a.entity.gus06.sys.draw1.builder.line;

import a.framework.*;
import java.awt.Color;
import java.util.Map;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250826";}


	public static final String KEY_POINT1 = "point1";
	public static final String KEY_POINT2 = "point2";
	public static final String KEY_THICKNESS = "thickness";
	public static final String KEY_COLOR = "color";
	

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
		private Point2D point1;
		private Point2D point2;
		private double thickness;
		private Color color;
		
		public Draw(Map map) throws Exception
		{
			point1 = (Point2D) findPoint2d.t(get(map, KEY_POINT1));
			point2 = (Point2D) findPoint2d.t(get(map, KEY_POINT2));
			thickness = toDouble(get(map, KEY_THICKNESS));
			color = (Color) findColor.t(get0(map, KEY_COLOR));
		}
		
		public void p(Object obj) throws Exception
		{
			Graphics2D g = (Graphics2D) obj;
			
			int x1 = (int) point1.getX();
			int y1 = (int) point1.getY();
			
			int x2 = (int) point2.getX();
			int y2 = (int) point2.getY();
			
			g.setColor(color);
			g.drawLine(x1, y1, x2, y2);
			
			//TODO prendre en compte le thickness
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