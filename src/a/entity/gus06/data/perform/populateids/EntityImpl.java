package a.entity.gus06.data.perform.populateids;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160229";}
	
	
	private Service performList;
	private Service performSet;
	private Service performMap;
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.populateids");
		performSet = Outside.service(this,"gus06.set.populateids");
		performMap = Outside.service(this,"gus06.map.populateids");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List) performList.p(obj);
		else if(input instanceof Set) performSet.p(obj);
		else if(input instanceof Map) performMap.p(obj);
		
		else throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
