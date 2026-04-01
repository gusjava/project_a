package a.entity.gus06.data.perform.sort.inv;

import a.framework.*;
import java.util.List;
import java.util.Collections;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20151125";}
	
	
	private Service findList;
	
	public EntityImpl() throws Exception
	{
		findList = Outside.service(this,"gus06.find.list");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof List) {sort((List) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List) return sort(new ArrayList((List) obj));
		
		if(obj instanceof Set) return sort(obj);
		if(obj instanceof Object[]) return sort(obj);
		if(obj instanceof int[]) return sort(obj);
		if(obj instanceof short[]) return sort(obj);
		if(obj instanceof long[]) return sort(obj);
		if(obj instanceof double[]) return sort(obj);
		if(obj instanceof float[]) return sort(obj);
		if(obj instanceof boolean[]) return sort(obj);
		if(obj instanceof char[]) return sort(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private List sort(List list)
	{
		Collections.sort(list,Collections.reverseOrder());
		return list;
	}
	
	private List sort(Object obj) throws Exception
	{return sort(toList(obj));}
	
	private List toList(Object obj) throws Exception
	{return (List) findList.t(obj);}
}
