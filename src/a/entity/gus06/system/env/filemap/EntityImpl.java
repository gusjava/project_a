package a.entity.gus06.system.env.filemap;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20171031";}

	
	
	public Object g() throws Exception
	{
		Map env = System.getenv();
		Map fileMap = new HashMap();
		
		Iterator it = env.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) env.get(key);
			
			String[] nn = value.split(";");
			if(nn.length==1) add(fileMap,key,value);
			else
			{
				for(int i=0;i<nn.length;i++)
				add(fileMap,key+"_"+i,value);
			}
		}
		return fileMap;
	}
	
	
	private void add(Map fileMap, String key, String value)
	{
		File file = new File(value);
		if(file.exists()) fileMap.put(key,file);
	}
}
