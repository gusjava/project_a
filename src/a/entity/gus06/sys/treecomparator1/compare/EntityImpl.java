package a.entity.gus06.sys.treecomparator1.compare;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190729";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map output = new HashMap();
		compare(output,"",o[0],o[1]);
		return output;
	}
	
	
	private void compare(Map output, String root, Object o1, Object o2)
	{
		if(o1==null && o2==null) return;
		
		if(o1==null || o2==null)
		{
			output.put(root,new Object[]{o1,o2});
			return;
		}
		
		
		if(o1 instanceof String && o2 instanceof String) 
		{compareStrings(output,root,(String)o1,(String) o2);return;}
		
		if(o1 instanceof Number && o2 instanceof Number) 
		{compareNumbers(output,root,(Number)o1,(Number) o2);return;}
		
		if(o1 instanceof Boolean && o2 instanceof Boolean) 
		{compareBooleans(output,root,(Boolean)o1,(Boolean) o2);return;}
		
		if(o1 instanceof Map && o2 instanceof Map) 
		{compareMaps(output,root,(Map)o1,(Map) o2);return;}
		
		if(o1 instanceof List && o2 instanceof List) 
		{compareLists(output,root,(List)o1,(List) o2);return;}
		
		if(o1 instanceof Object[] && o2 instanceof Object[]) 
		{compareArrays(output,root,(Object[])o1,(Object[]) o2);return;}
		
		
		output.put(root,new Object[]{o1,o2});
	}
	
	
	
	
	
	private void compareStrings(Map output, String root, String o1, String o2)
	{
		if(!o1.equals(o2)) output.put(root,new Object[]{o1,o2});
	}
	
	private void compareNumbers(Map output, String root, Number o1, Number o2)
	{
		if(!o1.equals(o2)) output.put(root,new Object[]{o1,o2});
	}
	
	private void compareBooleans(Map output, String root, Boolean o1, Boolean o2)
	{
		if(!o1.equals(o2)) output.put(root,new Object[]{o1,o2});
	}
	
	
	
	
	private void compareMaps(Map output, String root, Map o1, Map o2)
	{
		Set keys = new HashSet(o1.keySet());
		keys.addAll(o2.keySet());
		
		Iterator it = keys.iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value1 = get(o1,key);
			Object value2 = get(o2,key);
			
			compare(output,root+"|"+key,value1,value2);
		}
	}
	
	
	private void compareLists(Map output, String root, List o1, List o2)
	{
		int nb = Math.max(o1.size(),o2.size());
		for(int i=0;i<nb;i++)
		{
			Object el1 = get(o1,i);
			Object el2 = get(o2,i);
			compare(output,root+"|"+i,el1,el2);
		}
	}
	
	
	private void compareArrays(Map output, String root, Object[] o1, Object[] o2)
	{
		int nb = Math.max(o1.length,o2.length);
		for(int i=0;i<nb;i++)
		{
			Object el1 = get(o1,i);
			Object el2 = get(o2,i);
			compare(output,root+"|"+i,el1,el2);
		}
	}
	
	
	
	private Object get(Map map, Object key)
	{return map.containsKey(key) ? map.get(key) : null;}
	
	private Object get(List list, int index)
	{return index<list.size() ? list.get(index) : null;}
	
	private Object get(Object[] array, int index)
	{return index<array.length ? array[index] : null;}
}
