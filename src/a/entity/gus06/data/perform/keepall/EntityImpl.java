package a.entity.gus06.data.perform.keepall;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151124";}
	
	
	private Service performList;
	private Service performSet;
	private Service performMap;
	private Service performFile;
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.keepall");
		performSet = Outside.service(this,"gus06.set.keepall");
		performMap = Outside.service(this,"gus06.map.key.keepall");
		performFile = Outside.service(this,"gus06.dirfile.perform.keepall");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)
		{
			performList.p(obj);
			return;
		}
		
		if(input instanceof Set)
		{
			performSet.p(obj);
			return;
		}
		
		if(input instanceof Map)
		{
			performMap.p(obj);
			return;
		}
		
		if(input instanceof File)
		{
			performFile.p(obj);
			return;
		}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
