package a.entity.gus06.clipboard.access.file.asdir;

import a.framework.*;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, P, G {

	public String creationDate() {return "20250503";}


	private Service accessListFiles;


	public EntityImpl() throws Exception
	{
		accessListFiles = Outside.service(this,"gus.y.clipboard1.files");
	}
	
	
	public void p(Object obj) throws Exception
	{
		accessListFiles.p(obj);
	}

	
	
	public Object g() throws Exception
	{
		List list = (List) accessListFiles.g();
		
		if(list==null) return null;
		if(list.size()!=1) return null;
		File f = (File) list.get(0);
		
		return f.isDirectory() ? f : null;
	}
}
