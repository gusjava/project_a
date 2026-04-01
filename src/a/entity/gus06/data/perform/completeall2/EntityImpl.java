package a.entity.gus06.data.perform.completeall2;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180302";}
	
	
	private Service performMap;
	
	
	public EntityImpl() throws Exception
	{
		performMap = Outside.service(this,"gus06.map.completeall2");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		if(o[0] instanceof Map) {performMap.p(o);return;}
		
		throw new Exception("Invalid data type: "+o[0].getClass().getName());
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		if(o[0] instanceof Map) return performMap.t(o);
		
		throw new Exception("Invalid data type: "+o[0].getClass().getName());
	}
}
