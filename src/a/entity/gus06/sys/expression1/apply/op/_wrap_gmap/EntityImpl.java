package a.entity.gus06.sys.expression1.apply.op._wrap_gmap;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160301";}


	private Service buildMap;
	
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.map.wrap.gmap");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Map) return buildMap.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
