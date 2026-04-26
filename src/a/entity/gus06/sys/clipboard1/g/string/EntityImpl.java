package a.entity.gus06.sys.clipboard1.g.string;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160604";}


	private Service accessFile;
	private Service accessString;
	
	private Service convertFile;


	public EntityImpl() throws Exception
	{
		accessFile = Outside.service(this,"gus06.clipboard.access.file");
		accessString = Outside.service(this,"gus.x.clipboard.string");
		
		convertFile = Outside.service(this,"gus06.file.read.string.autodetect");
	}
	
	
	public Object g() throws Exception
	{
		String s = (String) accessString.g();
		if(s!=null) return s;
		
		File file = (File) accessFile.g();
		if(file!=null) return convertFile.t(file);
		
		return null;
	}
}
