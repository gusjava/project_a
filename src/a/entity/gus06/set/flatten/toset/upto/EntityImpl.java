package a.entity.gus06.set.flatten.toset.upto;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161218";}

	
	private Service findInt;

	public EntityImpl() throws Exception
	{
		findInt = Outside.service(this,"gus06.find.integer");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		int limit = ((Integer) findInt.t(o[1])).intValue();
		if(limit>=0) limit++;
		
		Set output = new HashSet();
		handleObj(output,input,0,limit);
		return output;
	}
	
	private boolean hasReached(int deep, int limit)
	{return limit>=0 && deep>=limit;}
	
	
	private void handleObj(Set output, Object input, int deep, int limit) throws Exception
	{
		if(hasReached(deep,limit))
			handleOther(output,input);
		
		else if(input instanceof List)
			handleList(output,(List) input,deep+1,limit);
			
		else if(input instanceof Set)
			handleSet(output,(Set) input,deep+1,limit);
			
		else if(input instanceof Map)
			handleMap(output,(Map) input,deep+1,limit);
			
		else if(input instanceof Object[])
			handleArray(output,(Object[]) input,deep+1,limit);
			
		else handleOther(output,input);
	}
	
	
	private void handleList(Set output, List input, int deep, int limit) throws Exception
	{
		for(int i=0;i<input.size();i++)
		{
			Object element = input.get(i);
			handleObj(output,element,deep,limit);
		}
	}
	
	private void handleSet(Set output, Set input, int deep, int limit) throws Exception
	{
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			handleObj(output,element,deep,limit);
		}
	}
	
	private void handleArray(Set output, Object[] input, int deep, int limit) throws Exception
	{
		for(int i=0;i<input.length;i++)
		{
			Object element = input[i];
			handleObj(output,element,deep,limit);
		}
	}
	
	private void handleMap(Set output, Map input, int deep, int limit) throws Exception
	{
		Iterator it = input.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = input.get(key);
			handleObj(output,value,deep,limit);
		}
	}
	
	private void handleOther(Set output, Object input) throws Exception
	{
		output.add(input);
	}
}
