package a.entity.gus06.sys.expression1.apply.op._linetomap;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160409";}


	private Service stringToMap;
	
	
	public EntityImpl() throws Exception
	{
		stringToMap = Outside.service(this,"gus06.map.string.stringtomap.builder2");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return stringToMap.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
