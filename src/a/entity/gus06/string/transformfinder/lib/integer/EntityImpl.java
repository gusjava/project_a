package a.entity.gus06.string.transformfinder.lib.integer;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150926";}
	
	public static final String OFFSET = "int_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("checkzero",Outside.service(this,"gus06.string.transform.integer.checkzero"));
		put("checkpos",Outside.service(this,"gus06.string.transform.integer.checkpos"));
		put("checkneg",Outside.service(this,"gus06.string.transform.integer.checkneg"));
		put("checkone",Outside.service(this,"gus06.string.transform.integer.checkone"));
		put("checkmany",Outside.service(this,"gus06.string.transform.integer.checkmany"));
		put("checkeven",Outside.service(this,"gus06.string.transform.integer.checkeven"));
		put("checkodd",Outside.service(this,"gus06.string.transform.integer.checkodd"));
		put("checkprime",Outside.service(this,"gus06.string.transform.integer.checkprime"));
		
		put("next",Outside.service(this,"gus06.string.transform.integer.next"));
		put("previous",Outside.service(this,"gus06.string.transform.integer.previous"));
		put("abs",Outside.service(this,"gus06.string.transform.integer.abs"));
		put("opp",Outside.service(this,"gus06.string.transform.integer.opp"));
		put("double",Outside.service(this,"gus06.string.transform.integer.double1"));
		put("triple",Outside.service(this,"gus06.string.transform.integer.triple"));
		put("square",Outside.service(this,"gus06.string.transform.integer.square"));
		put("nsum",Outside.service(this,"gus06.string.transform.integer.nsum"));
		put("factorial",Outside.service(this,"gus06.string.transform.integer.factorial"));
		
		put("tobinary",Outside.service(this,"gus06.string.transform.integer.tobinary"));
		put("tooctal",Outside.service(this,"gus06.string.transform.integer.tooctal"));
		put("tohexa",Outside.service(this,"gus06.string.transform.integer.tohexa"));
		
		put("clearzero",Outside.service(this,"gus06.string.transform.integer.clearzero"));
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(map.containsKey(s)) return map.get(s);
		return null;
	}
	
	public Object g() throws Exception
	{return map;}
}
