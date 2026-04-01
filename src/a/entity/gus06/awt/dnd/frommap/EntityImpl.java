package a.entity.gus06.awt.dnd.frommap;

import a.framework.*;
import java.util.Map;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180405";}
	
	public static final String KEY_COMP = "comp";
	public static final String KEY_G = "g";
	public static final String KEY_P = "p";
	public static final String KEY_PG = "pg";


	private Service handler;

	public EntityImpl() throws Exception
	{
		handler = Outside.service(this,"gus06.awt.dnd");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		JComponent comp = (JComponent) get1(map,KEY_COMP);
		
		Object g = get(map,KEY_G);
		Object p = get(map,KEY_P);
		Object pg = get(map,KEY_PG);
		
		if(pg!=null)
		{
			p = pg;
			g = pg;
		}
		handler.p(new Object[]{comp,p,g});
	}
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
