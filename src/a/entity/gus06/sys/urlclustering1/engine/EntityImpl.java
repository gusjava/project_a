package a.entity.gus06.sys.urlclustering1.engine;

import a.framework.*;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170113";}


	private Service buildMainKey;
	private Service findClusters;

	public EntityImpl() throws Exception
	{
		buildMainKey = Outside.service(this,"gus06.sys.urlclustering1.build.mainkey");
		findClusters = Outside.service(this,"gus06.sys.urlclustering1.build.clusters");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Collection c = (Collection) obj;
		
		Map m = new HashMap();
		
		Iterator it = c.iterator();
		while(it.hasNext())
		{
			URL url = (URL) it.next();
			String key = (String) buildMainKey.t(url);
			addToMap(m,key,url);
		}
		
		Set clusters = new HashSet();
		
		it = m.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Set set = (Set) m.get(key);
			
			Set clusters0 = (Set) findClusters.t(set);
			clusters.addAll(clusters0);
		}
		
		return clusters;
	}
	
	
	private void addToMap(Map m, String key, URL url)
	{
		if(!m.containsKey(key))
			m.put(key,new HashSet());
		((Set) m.get(key)).add(url);
	}
}
