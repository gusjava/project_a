package a.entity.gus06.sys.expression1.apply.op._kvkv_tomap;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180302";}


	private Service handleList;
	private Service handleArray;
	private Service findArray;
	
	public EntityImpl() throws Exception
	{
		handleList = Outside.service(this,"gus06.map.build.fromlist.kvkv");
		handleArray = Outside.service(this,"gus06.map.build.fromarray.kvkv");
		findArray = Outside.service(this,"gus06.find.objectarray");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List)		return handleList.t(obj);
		if(obj instanceof Object[])	return handleArray.t(obj);
		
		if(obj instanceof double[])	return handleArray.t(toArray(obj));
		if(obj instanceof int[])	return handleArray.t(toArray(obj));
		if(obj instanceof boolean[])	return handleArray.t(toArray(obj));
		if(obj instanceof long[])	return handleArray.t(toArray(obj));
		if(obj instanceof float[])	return handleArray.t(toArray(obj));
		if(obj instanceof byte[])	return handleArray.t(toArray(obj));
		if(obj instanceof short[])	return handleArray.t(toArray(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object[] toArray(Object obj) throws Exception
	{return (Object[]) findArray.t(obj);}
}
