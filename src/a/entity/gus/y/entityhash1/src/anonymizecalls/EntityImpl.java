package a.entity.gus.y.entityhash1.src.anonymizecalls;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260419";}
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) obj;
		
		src = src.replaceAll(
			"Outside\\.service\\(this,\s*\"[^\"]+\"\s*\\)",
			"Outside.service(this,\"?\")");
			
		src = src.replaceAll(
			"Outside\\.resource\\(this,\s*\"[^\"]+\"\s*\\)",
			"Outside.resource(this,\"?\")");
			
		return src;
	}
}
