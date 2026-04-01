package a.entity.gus06.sys.drawingpanel1.d.multi_cross1;

import a.framework.*;
import java.util.Map;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170426";}
	
	public static final String KEY_COLOR = "color";
	public static final String KEY_LIST = "list";
	public static final String KEY_LENGTH = "length";
	
	public static final Integer DEFAULT_LENGTH = Integer.valueOf(10);


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
			List list = (List) get1(map,KEY_LIST);
			int length = ((Integer) get0(map,KEY_LENGTH,DEFAULT_LENGTH)).intValue();
			int size = list.size();
			
			if(list.size()<2) return;
			
			Point2D[] pp = new Point2D[size];
			for(int i=0;i<size;i++)
			pp[i] = (Point2D) findPoint.t(new Object[]{list.get(i),panel,dimension});
			
			if(color!=null) g2.setPaint(color);
			
			for(int i=0;i<size;i++)
			{
				Point2D p1 = new Point2D.Double(pp[i].getX()-length,pp[i].getY());
				Point2D p2 = new Point2D.Double(pp[i].getX()+length,pp[i].getY());
				g2.draw(new Line2D.Double(p1,p2));
				
				p1 = new Point2D.Double(pp[i].getX(),pp[i].getY()-length);
				p2 = new Point2D.Double(pp[i].getX(),pp[i].getY()+length);
				g2.draw(new Line2D.Double(p1,p2));
			}
		}
	}
	
	
	private Object get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Object get0(Map map, String key, Object defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return map.get(key);
	}
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
