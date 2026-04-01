package a.entity.gus06.entitydev2.generatesrc;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251206";}
	
	public static final String KEY_TYPE = "type";


	private Service e1;
	private Service f1;
	private Service g1;
	private Service h1;
	private Service p1;
	private Service r1;
	private Service t1;


	public EntityImpl() throws Exception
	{
		e1 = Outside.service(this,"gus06.entitydev2.generatesrc.e1");
		f1 = Outside.service(this,"gus06.entitydev2.generatesrc.f1");
		g1 = Outside.service(this,"gus06.entitydev2.generatesrc.g1");
		h1 = Outside.service(this,"gus06.entitydev2.generatesrc.h1");
		p1 = Outside.service(this,"gus06.entitydev2.generatesrc.p1");
		r1 = Outside.service(this,"gus06.entitydev2.generatesrc.r1");
		t1 = Outside.service(this,"gus06.entitydev2.generatesrc.t1");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String type = get(map,KEY_TYPE);
		
		T t = findService(type);
		return t.t(map);
	}
	
	private T findService(String type) throws Exception
	{
		type = type.toUpperCase();
		if(type.equals("E")) return e1;
		if(type.equals("F")) return f1;
		if(type.equals("G")) return g1;
		if(type.equals("H")) return h1;
		if(type.equals("P")) return p1;
		if(type.equals("R")) return r1;
		if(type.equals("T")) return t1;
		
		throw new Exception("Unsupported type: "+type);
	}
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Unknown key inside map: "+key);
		return (String) map.get(key);
	}
}
