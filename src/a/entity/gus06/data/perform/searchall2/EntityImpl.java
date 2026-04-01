package a.entity.gus06.data.perform.searchall2;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180408";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		F filter = (F) o[1];
		
		List output = new ArrayList();
		handleObj(output,filter,"",input,input);
		return output;
	}
	
	private void handleObj(List output, F filter, String path, Object element, Object root) throws Exception
	{
		if(filter.f(new Object[]{element,root}))
		output.add(new Object[]{path,element});
		
		if(element instanceof List)		handleList(output,filter,path,(List) element,root);
		else if(element instanceof Set)		handleSet(output,filter,path,(Set) element,root);
		else if(element instanceof Map)		handleMap(output,filter,path,(Map) element,root);
		else if(element instanceof Object[])	handleArray(output,filter,path,(Object[]) element,root);
		else if(element instanceof File)	handleFile(output,filter,path,(File) element,root);
		
		else if(element instanceof String)	handleString(output,filter,path,(String) element,root);
		else if(element instanceof Number)	handleNumber(output,filter,path,(Number) element,root);
		else if(element instanceof Boolean)	handleBoolean(output,filter,path,(Boolean) element,root);
		
		else  throw new Exception("Invalid data type: "+element.getClass().getName());
	}
	
	private void handleList(List output, F filter, String path, List list, Object root) throws Exception
	{
		for(int i=0;i<list.size();i++)
		{
			String newPath = path+"."+i;
			Object element = list.get(i);
			handleObj(output,filter,newPath,element,root);
		}
	}
	
	private void handleArray(List output, F filter, String path, Object[] array, Object root) throws Exception
	{
		for(int i=0;i<array.length;i++)
		{
			String newPath = path+"."+i;
			Object element = array[i];
			handleObj(output,filter,newPath,element,root);
		}
	}
	
	private void handleSet(List output, F filter, String path, Set set, Object root) throws Exception
	{
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String newPath = path+".*";
			Object element = it.next();
			handleObj(output,filter,newPath,element,root);
		}
	}
	
	private void handleMap(List output, F filter, String path, Map map, Object root) throws Exception
	{
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object element = map.get(key);
			String newPath = path+"."+key;
			handleObj(output,filter,newPath,element,root);
		}
	}
	
	private void handleFile(List output, F filter, String path, File file, Object root) throws Exception
	{
		if(!file.isDirectory()) return;
		
		File[] ff = file.listFiles();
		if(ff!=null) for(int i=0;i<ff.length;i++)
		{
			File element = ff[i];
			String newPath = path+"."+element.getName();
			handleObj(output,filter,newPath,element,root);
		}
	}
	
	private void handleString(List output, F filter, String path, String s, Object root) throws Exception
	{
	}
	
	private void handleNumber(List output, F filter, String path, Number n, Object root) throws Exception
	{
	}
	
	private void handleBoolean(List output, F filter, String path, Boolean b, Object root) throws Exception
	{
	}
}
