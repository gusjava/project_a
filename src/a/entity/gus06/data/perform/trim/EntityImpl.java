package a.entity.gus06.data.perform.trim;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170201";}


	private Service performList;
	private Service performArray;
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.trim");
		performArray = Outside.service(this,"gus06.array.trim");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List) return performList.t(obj);
		if(obj instanceof Object[]) return performArray.t(obj);
		if(obj instanceof String) return performString(""+obj);
		if(obj instanceof Number) return performString(""+obj);
		if(obj instanceof Boolean) return performString(""+obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String performString(String s)
	{return s.trim();}
}
