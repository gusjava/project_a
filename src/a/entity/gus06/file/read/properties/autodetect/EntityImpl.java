package a.entity.gus06.file.read.properties.autodetect;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201126";}
	
	
	private Service readText;
	private Service stringToProp;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.autodetect");
		stringToProp = Outside.service(this,"gus06.convert.stringtoproperties");
	}
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		
		if(f==null) return null;
		if(!f.exists()) return null;
		if(f.length()==0) return null;
		
		String text = (String) readText.t(f);
		return stringToProp.t(text);
	}
}