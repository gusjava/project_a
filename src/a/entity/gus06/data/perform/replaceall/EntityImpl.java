package a.entity.gus06.data.perform.replaceall;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170429";}
	
	
	private Service performMap;
	private Service performList;
	private Service performSet;
	
	public EntityImpl() throws Exception
	{
		performMap = Outside.service(this,"gus06.map.replaceall");
		performList = Outside.service(this,"gus06.list.replaceall");
		performSet = Outside.service(this,"gus06.set.replaceall");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		if(o[0] instanceof Map) {performMap.p(o);return;}
		if(o[0] instanceof List) {performList.p(o);return;}
		if(o[0] instanceof Set) {performSet.p(o);return;}
		
		throw new Exception("Invalid data type: "+o[0].getClass().getName());
	}
}
