package a.entity.gus06.file.read.properties.autodetect.init;

import java.io.File;
import a.framework.*;
import java.util.Properties;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221009";}
	
	
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
		
		if(f==null) return new Properties();
		if(!f.exists()) return new Properties();
		if(f.length()==0) return new Properties();
		
		String text = (String) readText.t(f);
		return stringToProp.t(text);
	}
}
