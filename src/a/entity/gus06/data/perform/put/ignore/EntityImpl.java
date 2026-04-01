package a.entity.gus06.data.perform.put.ignore;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161205";}
	
	
	private Service performMap;
	private Service performFile;
	
	public EntityImpl() throws Exception
	{
		performMap = Outside.service(this,"gus06.map.put.ignore");
		performFile = Outside.service(this,"gus06.file.properties.perform.field.put.ignore");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		if(o[0] instanceof Map) {performMap.p(o);return;}
		if(o[0] instanceof File) {performFile.p(o);return;}
		throw new Exception("Invalid data type: "+o[0].getClass().getName());
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		if(o[0] instanceof Map) return performMap.t(o);
		throw new Exception("Invalid data type: "+o[0].getClass().getName());
	}
}
