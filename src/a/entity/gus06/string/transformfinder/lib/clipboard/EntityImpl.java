package a.entity.gus06.string.transformfinder.lib.clipboard;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20160517";}
	
	public static final String OFFSET = "wrap_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("q1",Outside.service(this,"gus06.string.transform.wrap.q1"));
		put("q2",Outside.service(this,"gus06.string.transform.wrap.q2"));
		put("q3",Outside.service(this,"gus06.string.transform.wrap.q3"));
		put("q4",Outside.service(this,"gus06.string.transform.wrap.q4"));
		put("q5",Outside.service(this,"gus06.string.transform.wrap.q5"));
		
		put("qr1",Outside.service(this,"gus06.string.transform.wrap.qr1"));
		put("qr2",Outside.service(this,"gus06.string.transform.wrap.qr2"));
		put("qr3",Outside.service(this,"gus06.string.transform.wrap.qr3"));
		put("qr4",Outside.service(this,"gus06.string.transform.wrap.qr4"));
		put("qr5",Outside.service(this,"gus06.string.transform.wrap.qr5"));
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
