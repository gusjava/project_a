package a.entity.gus06.y.openrouter.gui3models.filterlist;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251127";}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		List data = (List) o[0];
		String search = ((String) o[1]).toLowerCase();
		Boolean free = (Boolean) o[2];
		
		if(data==null) return null;
		
		List data0 = new ArrayList();
		for(int i=0;i<data.size();i++)
		{
			Map item = (Map) data.get(i);
			if(filter(item, search, free)) data0.add(item);
		}
		return data0;
	}
	
	private boolean filter(Map item, String search, Boolean free)
	{
		if(free!=null && free && hasPrice(item)) return false; 
		String id = (String) item.get("id");
		return id.toLowerCase().contains(search);
	}
	
	private boolean hasPrice(Map item)
	{
		Map m = (Map) item.get("pricing");
		String price = (String) m.get("completion");
		return !price.equals("0");
	}
}
