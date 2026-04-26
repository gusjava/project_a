package a.entity.gus06.file.properties.perform.each.keyvalue.transform;

import java.io.File;
import a.framework.*;
import java.util.Map;
import java.util.Properties;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20150926";}


	private Service readFile;
	private Service writeFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
		writeFile = Outside.service(this,"gus06.file.write.properties");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		
		Properties prop1 = (Properties) t(obj);
		writeFile.p(new Object[]{file,prop1});
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		T t = (T) o[1];
		
		Map prop = (Map) readFile.t(file);
		Properties prop1 = new Properties();
		
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) prop.get(key);
			
			String key1 = (String) t.t(key);
			String value1 = (String) t.t(value);
			
			if(prop1.containsKey(key1))
				throw new Exception("Duplicated key found: "+key1);
			prop1.setProperty(key1,value1);
		}
		return prop1;
	}
}