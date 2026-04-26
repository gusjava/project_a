package a.entity.gus06.file.modify.properties.keyvalue;

import a.framework.*;
import java.io.File;
import java.util.Properties;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140905";}


	private Service write;
	private Service read;


	public EntityImpl() throws Exception
	{
		write = Outside.service(this,"gus06.file.write.properties");
		read = Outside.service(this,"gus.x.file.prop.read");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String key = (String) o[1];
		String value = (String) o[2];
		
		Properties prop = (Properties) read.t(file);
		if(prop==null) prop = new Properties();
		
		if(value==null) prop.remove(key);
		else prop.setProperty(key,value);
		
		write.p(new Object[]{file,prop});
	}
}
