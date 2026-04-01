package a.entity.gus06.sys.dirdoubloon1.build.summary1;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221218";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map md5Map = (Map) obj;
		
		long totalLost = 0;
		Integer totalNb = 0;
		Integer groupNb = md5Map.size();
		
		Iterator it = md5Map.keySet().iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			Map m = (Map) md5Map.get(md5);
			
			Long lost = (Long) m.get("lost");
			totalLost += lost;
			
			Integer nb = (Integer) m.get("nb");
			totalNb += nb;
		}
		
		Map summary = new HashMap();
		summary.put("totalLost",totalLost);
		summary.put("totalNb",totalNb);
		summary.put("groupNb",groupNb);
		
		return summary;
	}
}
