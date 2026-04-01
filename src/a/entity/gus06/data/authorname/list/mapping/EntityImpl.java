package a.entity.gus06.data.authorname.list.mapping;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220911";}


	private Service norm;
	private Service findBest;
	private Service groupBy;

	public EntityImpl() throws Exception
	{
		norm = Outside.service(this,"gus06.string.transform.normalize.authorname");
		findBest = Outside.service(this,"gus06.data.authorname.list.findbest");
		groupBy = Outside.service(this,"gus06.list.groupby");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		list.remove(null);
		
		Map authorGroups = (Map) groupBy.t(new Object[]{list, norm});
		if(authorGroups.containsKey(null))
			throw new Exception("authorsGroup contains null with value: "+authorGroups.get(null));
		
		Map mapping = new HashMap();
		Iterator it = authorGroups.keySet().iterator();
		while(it.hasNext())
		{
			String normalizedName = (String) it.next();
			List group = (List) authorGroups.get(normalizedName);
			String bestName = findBestName(group, normalizedName);
			
			for(Object authorName : group)
			mapping.put(authorName,bestName);
		}
		return mapping;
	}
	
	
	private String findBestName(List group, String normalizedName) throws Exception
	{
		try
		{
			return (String) findBest.t(group);
		}
		catch(Exception e)
		{
			String message = "Failed to find best name for normalized name: "+normalizedName;
			throw new Exception(message, e);
		}
	}
}