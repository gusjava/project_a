package a.entity.gus06.data.perform.totalnb;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161214";}


	private Service performMap;
	private Service performSet;
	private Service performList;
	private Service performArray;
	
	public EntityImpl() throws Exception
	{
		performMap = Outside.service(this,"gus06.map.count.total");
		performSet = Outside.service(this,"gus06.set.count.total");
		performList = Outside.service(this,"gus06.list.count.total");
		performArray = Outside.service(this,"gus06.array.count.total");
		
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return performMap.t(obj);
		if(obj instanceof Set) return performSet.t(obj);
		if(obj instanceof List) return performList.t(obj);
		if(obj instanceof Object[]) return performArray.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
