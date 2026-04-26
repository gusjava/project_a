package a.entity.gus06.clipboard.access.string.or.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20210717";}


	private Service accessFile;
	private Service accessString;

	public EntityImpl() throws Exception
	{
		accessFile = Outside.service(this,"gus06.clipboard.access.file");
		accessString = Outside.service(this,"gus.x.clipboard.string");
	}
	
	public Object g() throws Exception
	{
		String s = (String) accessString.g();
		if(s!=null) return s;
		
		File file = (File) accessFile.g();
		if(file!=null) return file;
		
		return null;
	}
}