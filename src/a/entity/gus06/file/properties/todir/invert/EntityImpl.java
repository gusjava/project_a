package a.entity.gus06.file.properties.todir.invert;

import a.framework.*;
import java.io.File;
import java.util.Properties;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150619";}


	private Service readFile;
	private Service getName;
	private Service normalize;
	private Service modify;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
		getName = Outside.service(this,"gus06.file.getname0");
		normalize = Outside.service(this,"gus06.string.transform.normalize.filename");
		modify = Outside.service(this,"gus06.file.modify.properties.keyvalue");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = o[0];
		File dir = o[1];
		
		String name = (String) getName.t(file);
		Properties p = (Properties) readFile.t(file);
			
		Iterator it = p.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = p.getProperty(key);
				
			File file1 = new File(dir,toFileName(key));
			modify.p(new Object[]{file1,name,value});
		}
	}
	
	
	
	private String toFileName(String key) throws Exception
	{
		String key_ = (String) normalize.t(key);
		return key_+".properties";
	}
}
