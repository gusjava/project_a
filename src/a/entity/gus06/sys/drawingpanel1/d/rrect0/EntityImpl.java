package a.entity.gus06.sys.drawingpanel1.d.rrect0;

import a.framework.*;
import java.util.Map;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170820";}
	
	public static final String KEY_COLOR = "color";
	public static final String KEY_START = "start";
	public static final String KEY_END = "end";
	public static final String KEY_WIDTH = "width";
	public static final String KEY_HEIGHT = "height";
	public static final String KEY_ARCW = "arcw";
	public static final String KEY_ARCH = "arch";


	private Service findPoint;

	public EntityImpl() throws Exception
	{
		findPoint = Outside.service(this,"gus06.sys.drawingpanel1.build.point2d");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((Map) obj);
	}
	
	
	private class Holder implements P, V
	{
		private Map map;
		private Object dimension;
		
		public Holder(Map map)
		{this.map = map;}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("dimension")) {dimension = obj;return;}
			throw new Exception("Unknown key: "+key);
		}
		
		public void p(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			Graphics2D g2 = (Graphics2D) o[0];
			JPanel panel = (JPanel) o[1];
			
			Color color = (Color) get0(map,KEY_COLOR);
			if(color!=null) g2.setPaint(color);
			
			Object start = get1(map,KEY_START);
			
			Point2D p1 = (Point2D) findPoint.t(new Object[]{start,panel,dimension});
			double x = p1.getX();
			double y = p1.getY();
			
			double arcw0 = ((Number) get1(map,KEY_ARCW)).doubleValue();
			double arch0 = ((Number) get1(map,KEY_ARCH)).doubleValue();
			
			Point2D arcwh = (Point2D) findPoint.t(new Object[]{new double[]{arcw0,arch0},panel,dimension});
			double arcw = arcwh.getX();
			double arch = arcwh.getY();
				
			Object end = get0(map,KEY_END);
			if(end!=null)
			{
				Point2D p2 = (Point2D) findPoint.t(new Object[]{end,panel,dimension});
				double w = p2.getX()-x;
				double h = p2.getY()-y;
				
				g2.draw(new RoundRectangle2D.Double(x,y,w,h,arcw,arch));
				return;
			}
			
			double w0 = ((Number) get1(map,KEY_WIDTH)).doubleValue();
			double h0 = ((Number) get1(map,KEY_HEIGHT)).doubleValue();
			
			Point2D wh = (Point2D) findPoint.t(new Object[]{new double[]{w0,h0},panel,dimension});
			double w = wh.getX();
			double h = wh.getY();
				
			g2.draw(new RoundRectangle2D.Double(x,y,w,h,arcw,arch));
		}
	}
	
	
		
	
	
	private Object get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
