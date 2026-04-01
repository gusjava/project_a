package a.entity.gus06.map.deep.put.remove;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151204";}


	private Service nextData;
	private Service ruleToIndex;

	public EntityImpl() throws Exception
	{
		nextData = Outside.service(this,"gus06.map.deep.nextdata0");
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2)	throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		String key = (String) o[1];
		
		String[] n = key.split("\\.");
		String lastKey = n[n.length-1];
		
		for(int i=0;i<n.length-1;i++)
		data = nextData(data,n[i]);
		
		if(data!=null) removeValue(data,lastKey);
	}
	
	
	private Object nextData(Object data, String key) throws Exception
	{return nextData.t(new Object[]{data,key});}
	
	
	
	
	private void removeValue(Object data, String k) throws Exception
	{
		if(data instanceof Map)
		{
			Map m = (Map) data;
			if(m.containsKey(k)) m.remove(k);
			return;
		}
		if(data instanceof List)
		{
			List l = (List) data;
			Integer n = (Integer) ruleToIndex.t(new Object[]{l,k});
			if(n!=null) l.remove(n.intValue());
			return;
		}
		if(data instanceof V)
		{
			V v = (V) data;
			v.v(k,null);
			return;
		}
		if(data instanceof P)
		{
			P p = (P) data;
			if(k.equals("p")) p.p(null);
			return;
		}
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
}
