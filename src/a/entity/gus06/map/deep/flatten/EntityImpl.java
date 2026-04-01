package a.entity.gus06.map.deep.flatten;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220428";}


	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Map output = new HashMap();
		handleObject(output, null, obj);
		return output;
	}
	
	
	
	private void handleObject(Map output, String offset, Object input) throws Exception
	{
		if(input==null) output.put(offset, null);
		else if(input instanceof Map)
			handleMap(output,offset,(Map) input);
		else if(input instanceof List)
			handleList(output,offset,(List) input);
		else if(input instanceof Set)
			handleSet(output,offset,(Set) input);
		else output.put(offset, input);
	}
	
	
	private void handleMap(Map output, String offset, Map input) throws Exception
	{
		Iterator it = input.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(!(key instanceof String)) throw new Exception("Invalid key type for ["+key+"] : "+key.getClass().getSimpleName());
			
			String key1 = (String) key;
			if(key1.contains(".")) throw new Exception("Invalid key detected for deep structure: "+key);
			
			Object value = input.get(key1);
			String deepKey = offset!=null ? offset+"."+key1 : key1;
			handleObject(output, deepKey, value);
		}
	}
	
	
	private void handleList(Map output, String offset, List input) throws Exception
	{
		for(int i=0;i<input.size();i++)
		{
			Object value = input.get(i);
			String deepKey = offset!=null ? offset+"."+i : ""+i;
			handleObject(output, deepKey, value);
		}
	}
	
	
	private void handleSet(Map output, String offset, Set input) throws Exception
	{
		handleList(output, offset, new ArrayList(input)); 
	}
}