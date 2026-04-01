package a.entity.gus06.sys.urlclustering1.build.clusters;

import a.framework.*;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170113";}

	
	
	public Object t(Object obj) throws Exception
	{
		Collection c = (Collection) obj;
		
		Map m = new HashMap();
		
		Iterator it = c.iterator();
		while(it.hasNext())
		{
			URL url = (URL) it.next();
			String path0 = path0(url.getPath());
			addToMap(m,path0,url);
		}
		return new HashSet(m.values());
	}
	
	
	private void addToMap(Map m, String key, URL url)
	{
		if(!m.containsKey(key))
			m.put(key,new HashSet());
		((Set) m.get(key)).add(url);
	}
	
	
	private String path0(String path)
	{
		String[] nn = path.split("/");
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nn.length-1;i++) b.append(nn[i]+"/");
		return b.toString();
	}
}
