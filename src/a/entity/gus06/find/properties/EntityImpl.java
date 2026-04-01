package a.entity.gus06.find.properties;

import a.framework.*;
import java.util.Properties;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140831";}


	private Service chkNotNull;
	
	public EntityImpl() throws Exception
	{
		chkNotNull = Outside.service(this,"gus06.map.chk.value.notnull");
	}


	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Properties) return obj;
		if(obj instanceof Map) return mapToProp((Map) obj);
		if(obj instanceof File) return readProp((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Properties mapToProp(Map map) throws Exception
	{
		chkNotNull.p(map);
		Properties prop = new Properties();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			prop.put(""+key, ""+value);
		}
		return prop;
	}
	
	
	
	private Properties readProp(File file) throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		fis.close();
		return prop;
	}
}
