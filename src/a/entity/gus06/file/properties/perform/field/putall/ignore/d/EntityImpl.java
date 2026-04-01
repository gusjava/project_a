package a.entity.gus06.file.properties.perform.field.putall.ignore.d;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20161218";}

	

	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Map map = (Map) o[1];
		
		Properties prop = prop(file);
		
		Map map1 = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(!prop.containsKey(key)) map1.put(key,map.get(key));
			else System.out.println("key ignored: "+key);
		}
		
		prop.putAll(map1);
		save(prop,file);
		return true;
	}

	
	
	private Properties prop(File file) throws Exception
	{
		Properties p = new Properties();
		if(file.exists())
		{
			FileInputStream fis = new FileInputStream(file);
			p.load(fis);
			fis.close();
		}
		return p;
	}
	
	private void save(Properties p, File file) throws Exception
	{
		file.getParentFile().mkdirs();
		FileOutputStream fos = new FileOutputStream(file);
		p.store(fos,"");
		fos.close();
	}
}