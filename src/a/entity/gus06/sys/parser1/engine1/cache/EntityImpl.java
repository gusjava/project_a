package a.entity.gus06.sys.parser1.engine1.cache;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170213";}
	
	private Service engine1;
	private Map map;


	public EntityImpl() throws Exception
	{
		engine1 = Outside.service(this,"gus06.sys.parser1.engine1");
		map = new HashMap();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String info = (String) obj;
		if(!map.containsKey(info))
			map.put(info,buildParser(info));
		return (T) map.get(info);
	}
	
	private T buildParser(String info) throws Exception
	{return (T) engine1.t(info);}
}
