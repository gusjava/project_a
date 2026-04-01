package a.entity.gus06.data.perform.addall.strict;

import a.framework.*;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160712";}
	
	
	private Service performSet;
	private Service performList;
	
	public EntityImpl() throws Exception
	{
		performSet = Outside.service(this,"gus06.set.addall.strict");
		performList = Outside.service(this,"gus06.list.addall.strict");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof Set) {performSet.p(obj);return;}
		if(input instanceof List) {performList.p(obj);return;}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof Set) return performSet.t(obj);
		if(input instanceof List) return performList.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
