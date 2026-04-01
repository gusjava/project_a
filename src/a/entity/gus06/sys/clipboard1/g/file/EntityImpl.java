package a.entity.gus06.sys.clipboard1.g.file;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20151023";}


	private Service accessListFiles;


	public EntityImpl() throws Exception
	{
		accessListFiles = Outside.service(this,"gus06.sys.clipboard1.g.listfiles");
	}

	
	
	public Object g() throws Exception
	{
		List list = (List) accessListFiles.g();
		
		if(list==null) return null;
		if(list.size()!=1) return null;
		return list.get(0);
	}
}
