package a.entity.gus06.sys.clipboard1.g.map;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160603";}


	private Service accessFile;
	private Service accessString;
	
	private Service convertFile;
	private Service convertString;


	public EntityImpl() throws Exception
	{
		accessFile = Outside.service(this,"gus06.clipboard.access.file");
		accessString = Outside.service(this,"gus.x.clipboard.string");
		
		convertFile = Outside.service(this,"gus06.file.read.properties.generic");
		convertString = Outside.service(this,"gus06.convert.stringtomap");
	}
	
	
	public Object g() throws Exception
	{
		File file = (File) accessFile.g();
		if(file!=null) return convertFile.t(file);
		
		String s = (String) accessString.g();
		if(s!=null) return convertString.t(s);
		
		return null;
	}
}
