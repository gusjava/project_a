package a.entity.gus06.sys.clipboard1.g.string.filepath;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20230120";}


	private Service accessFile;
	private Service accessString;

	public EntityImpl() throws Exception
	{
		accessFile = Outside.service(this,"gus06.clipboard.access.file");
		accessString = Outside.service(this,"gus06.clipboard.access.string");
	}
	
	
	public Object g() throws Exception
	{
		String s = (String) accessString.g();
		if(s!=null) return s;
		
		File file = (File) accessFile.g();
		if(file!=null) return file.getAbsolutePath();
		
		return null;
	}
}