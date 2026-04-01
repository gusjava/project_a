package a.entity.gus06.app.entity.nametocalls;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140827";}


	private Service retrieveSrcCode;
	private Service extractSer;
	private Service extractRes;
	
	
	public EntityImpl() throws Exception
	{
		retrieveSrcCode = Outside.service(this,"gus06.entitydev.retrieve.srccode1");
		extractSer = Outside.service(this,"gus06.java.srccode.extract.entity.calls.service");
		extractRes = Outside.service(this,"gus06.java.srccode.extract.entity.calls.resource");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String entityName = (String) obj;
		String src = (String) retrieveSrcCode.t(entityName);
		
		List services = (List) extractSer.t(src);
		List resources = (List) extractRes.t(src);
		
		List calls = new ArrayList();
		calls.addAll(services);
		calls.addAll(resources);
		
		return calls;
	}
}
