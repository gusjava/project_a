package a.entity.gus06.map.flatten.tomap;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161031";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object input = obj;
		Map output = new HashMap();
		
		handleObj("",output,input);
		return output;
	}
	
	private void handleObj(String path, Map output, Object input) throws Exception
	{
		if(input instanceof List)
			handleList(path,output,(List) input);
		else if(input instanceof Set)
			handleSet(path,output,(Set) input);
		else if(input instanceof Map)
			handleMap(path,output,(Map) input);
		else if(input instanceof Object[])
			handleArray(path,output,(Object[]) input);
			
		else handleOther(path,output,input);
	}
	
	
	private void handleList(String path, Map output, List input) throws Exception
	{
		for(int i=0;i<input.size();i++)
		{
			String newPath = path+"."+i;
			Object element = input.get(i);
			handleObj(newPath,output,element);
		}
	}
	
	private void handleSet(String path, Map output, Set input) throws Exception
	{
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			String newPath = path+".*";
			Object element = it.next();
			handleObj(newPath,output,element);
		}
	}
	
	private void handleArray(String path, Map output, Object[] input) throws Exception
	{
		for(int i=0;i<input.length;i++)
		{
			String newPath = path+"."+i;
			Object element = input[i];
			handleObj(newPath,output,element);
		}
	}
	
	private void handleMap(String path, Map output, Map input) throws Exception
	{
		Iterator it = input.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object value = input.get(key);
			String newPath = path+"."+key;
			handleObj(newPath,output,value);
		}
	}
	
	private void handleOther(String path, Map output, Object input) throws Exception
	{
		if(path.startsWith(".")) path = path.substring(1);
		output.put(path,input);
	}
}
