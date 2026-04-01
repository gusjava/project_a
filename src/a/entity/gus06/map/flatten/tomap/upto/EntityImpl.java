package a.entity.gus06.map.flatten.tomap.upto;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;

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
		
		Map output = new HashMap();
		handleObj("",output,input,0,limit);
		return output;
	}
	
	private boolean hasReached(int deep, int limit)
	{return limit>=0 && deep>=limit;}
	
	
	private void handleObj(String path, Map output, Object input, int deep, int limit) throws Exception
	{
		if(hasReached(deep,limit))
			handleOther(path,output,input);
		
		else if(input instanceof List)
			handleList(path,output,(List) input,deep+1,limit);
		else if(input instanceof Set)
			handleSet(path,output,(Set) input,deep+1,limit);
		else if(input instanceof Map)
			handleMap(path,output,(Map) input,deep+1,limit);
		else if(input instanceof Object[])
			handleArray(path,output,(Object[]) input,deep+1,limit);
			
		else handleOther(path,output,input);
	}
	
	
	private void handleList(String path, Map output, List input, int deep, int limit) throws Exception
	{
		for(int i=0;i<input.size();i++)
		{
			String newPath = path+"."+i;
			Object element = input.get(i);
			handleObj(newPath,output,element,deep,limit);
		}
	}
	
	private void handleSet(String path, Map output, Set input, int deep, int limit) throws Exception
	{
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			String newPath = path+".*";
			Object element = it.next();
			handleObj(newPath,output,element,deep,limit);
		}
	}
	
	private void handleArray(String path, Map output, Object[] input, int deep, int limit) throws Exception
	{
		for(int i=0;i<input.length;i++)
		{
			String newPath = path+"."+i;
			Object element = input[i];
			handleObj(newPath,output,element,deep,limit);
		}
	}
	
	private void handleMap(String path, Map output, Map input, int deep, int limit) throws Exception
	{
		Iterator it = input.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object value = input.get(key);
			String newPath = path+"."+key;
			handleObj(newPath,output,value,deep,limit);
		}
	}
	
	private void handleOther(String path, Map output, Object input) throws Exception
	{
		if(path.startsWith(".")) path = path.substring(1);
		output.put(path,input);
	}
}
