package a.entity.gus06.swing.action.builder0.frommap;

import a.framework.*;
import java.util.Map;
import javax.swing.Action;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170314";}
	
	public static final String KEY_EXECUTE = "execute";
	public static final String KEY_DISPLAY = "display";
	public static final String KEY_TOOLTIP = "tooltip";
	public static final String KEY_ENABLER = "enabler";


	private Service builder;
	private Service custEnable;

	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.swing.action.builder0");
		custEnable = Outside.service(this,"gus06.swing.action.cust.enable");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		E execute = (E) get1(map,KEY_EXECUTE);
		String display = (String) get1(map,KEY_DISPLAY);
		
		Action action = (Action) builder.t(new Object[]{display,execute});
		
		String tooltip = (String) get0(map,KEY_TOOLTIP);
		if(tooltip!=null) action.putValue(Action.SHORT_DESCRIPTION, tooltip);
		
		S enabler = (S) get0(map,KEY_ENABLER);
		if(enabler!=null) custEnable.p(new Object[]{action,enabler});
		
		return action;
	}
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	private Object get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
