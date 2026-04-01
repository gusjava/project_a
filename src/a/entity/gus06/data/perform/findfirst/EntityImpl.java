package a.entity.gus06.data.perform.findfirst;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200326";}
	
	
	private Service performList;
	private Service performArray;
	private Service performDoubleArray;
	private Service performIntArray;
	
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.findfirst");
		performArray = Outside.service(this,"gus06.array.objectarray.findfirst");
		performDoubleArray = Outside.service(this,"gus06.array.doublearray.findfirst");
		performIntArray = Outside.service(this,"gus06.array.intarray.findfirst");
	}	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List) return performList.t(obj);
		if(input instanceof Object[]) return performArray.t(obj);
		if(input instanceof double[]) return performDoubleArray.t(obj);
		if(input instanceof int[]) return performIntArray.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
