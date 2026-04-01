package a.entity.gus06.map.build.stringmap1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170518";}


	private Service listToString;
	private Service setToString;
	private Service arrayToString;
	private Service dateToString;
	
	
	public EntityImpl() throws Exception
	{
		listToString = Outside.service(this,"gus06.tostring.list.join.semicolon");
		setToString = Outside.service(this,"gus06.tostring.set.join.semicolon");
		arrayToString = Outside.service(this,"gus06.tostring.array.join.semicolon");
		dateToString = Outside.service(this,"gus06.tostring.desc.short1.date");
	}



	public Object t(Object obj) throws Exception
	{
		Map m = (Map) obj;
		Map r = new HashMap();
		
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = m.get(key);
			
			r.put(handle(key),handle(value));
		}
		return r;
	}
	
	
	private String handle(Object obj) throws Exception
	{
		if(obj==null) return "null";
		if(obj instanceof String) return (String) obj;
		if(obj instanceof List) return (String) listToString.t(obj);
		if(obj instanceof Set) return (String) setToString.t(obj);
		if(obj instanceof Object[]) return (String) listToString.t(obj);
		if(obj instanceof Date) return (String) dateToString.t(obj);
		
		return ""+obj;
	}
}
