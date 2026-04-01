package a.entity.gus06.data.perform.count;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170118";}


	private Service performString;
	private Service performList;
	private Service performSet;
	private Service findList;
	
	
	public EntityImpl() throws Exception
	{
		performString = Outside.service(this,"gus06.data.string.freqmap.countchars");
		performList = Outside.service(this,"gus06.list.count");
		performSet = Outside.service(this,"gus06.set.count");
		findList = Outside.service(this,"gus06.find.list");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null)				return new HashMap();
		
		if(obj instanceof String)		return performString.t(obj);
		if(obj instanceof StringBuffer)		return performString.t(obj.toString());
		if(obj instanceof StringBuilder)	return performString.t(obj.toString());
		if(obj instanceof List)			return performList.t(obj);
		if(obj instanceof Set)			return performSet.t(obj);
		
		if(obj instanceof Object[][])		return performList.t(findList.t(obj));
		if(obj instanceof int[][])		return performList.t(findList.t(obj));
		if(obj instanceof short[][])		return performList.t(findList.t(obj));
		if(obj instanceof long[][])		return performList.t(findList.t(obj));
		if(obj instanceof double[][])		return performList.t(findList.t(obj));
		if(obj instanceof float[][])		return performList.t(findList.t(obj));
		if(obj instanceof boolean[][])		return performList.t(findList.t(obj));
		if(obj instanceof char[][])		return performList.t(findList.t(obj));
		
		if(obj instanceof Object[])		return performList.t(findList.t(obj));
		if(obj instanceof int[])		return performList.t(findList.t(obj));
		if(obj instanceof short[])		return performList.t(findList.t(obj));
		if(obj instanceof long[])		return performList.t(findList.t(obj));
		if(obj instanceof double[])		return performList.t(findList.t(obj));
		if(obj instanceof float[])		return performList.t(findList.t(obj));
		if(obj instanceof boolean[])		return performList.t(findList.t(obj));
		if(obj instanceof char[])		return performList.t(findList.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}