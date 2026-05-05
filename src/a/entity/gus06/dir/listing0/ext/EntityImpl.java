package a.entity.gus06.dir.listing0.ext;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220410";}
	
	
	private Service buildFilter;

	public EntityImpl() throws Exception
	{
		buildFilter = Outside.service(this,"gus.x.file.filter.build.ext");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object ext = o[1];
		
		FileFilter fileFilter = (FileFilter) buildFilter.t(ext);
		return dir.listFiles(fileFilter);
	}
}