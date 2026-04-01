package a.entity.gus06.data.perform.inject;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170327";}
	
	
	private Service performList;
	private Service performArray;
	private Service performString;
	
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.inject");
		performArray = Outside.service(this,"gus06.array.objectarray.inject");
		performString = Outside.service(this,"gus06.data.string.inject");
	}
	

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List) return performList.t(obj);
		if(input instanceof Object[]) return performArray.t(obj);
		if(input instanceof String) return performString.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
