package a.entity.gus06.list.flatten.tolist;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161217";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object input = obj;
		List output = new ArrayList();
		handleObj(output,input);
		return output;
	}
	
	
	private void handleObj(List output, Object input) throws Exception
	{
		if(input instanceof List)
			handleList(output,(List) input);
			
		else if(input instanceof Set)
			handleSet(output,(Set) input);
			
		else if(input instanceof Map)
			handleMap(output,(Map) input);
			
		else if(input instanceof Object[])
			handleArray(output,(Object[]) input);
			
		else handleOther(output,input);
	}
	
	
	private void handleList(List output, List input) throws Exception
	{
		for(int i=0;i<input.size();i++)
		{
			Object element = input.get(i);
			handleObj(output,element);
		}
	}
	
	private void handleSet(List output, Set input) throws Exception
	{
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			handleObj(output,element);
		}
	}
	
	private void handleArray(List output, Object[] input) throws Exception
	{
		for(int i=0;i<input.length;i++)
		{
			Object element = input[i];
			handleObj(output,element);
		}
	}
	
	private void handleMap(List output, Map input) throws Exception
	{
		Iterator it = input.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = input.get(key);
			handleObj(output,value);
		}
	}
	
	private void handleOther(List output, Object input) throws Exception
	{
		output.add(input);
	}
}
