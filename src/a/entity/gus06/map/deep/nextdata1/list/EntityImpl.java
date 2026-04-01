package a.entity.gus06.map.deep.nextdata1.list;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160312";}


	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex1");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2)	throw new Exception("Wrong data number: "+o.length);
		
		List data = (List) o[0];
		String key = (String) o[1];
		
		
		if(key.equals("after"))
		{
			Map m = new HashMap();
			data.add(m);
			return m;
		}
		
		if(key.equals("before"))
		{
			Map m = new HashMap();
			data.add(0,m);
			return m;
		}
		
		if(data.isEmpty())
		{
			int index = Integer.parseInt(key);
			if(index<0) return null;
		
			while(index>=data.size())
				data.add(null);
			if(data.get(index)==null)
				data.set(index,new HashMap());
			return data.get(index);
		}
		
		Integer n = (Integer) ruleToIndex.t(obj);
		if(n==null) return null;
		
		int index = n.intValue();
		if(index<0) return null;
		
		while(index>=data.size())
			data.add(null);
		if(data.get(index)==null)
			data.set(index,new HashMap());
		return data.get(index);
	}
}
