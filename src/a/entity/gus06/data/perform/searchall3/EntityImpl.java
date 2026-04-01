package a.entity.gus06.data.perform.searchall3;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180411";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		F filter = (F) o[1];
		
		List output = new ArrayList();
		Map m = new HashMap();
		
		m.put("root",input);
		m.put("filter",filter);
		m.put("output",output);
		m.put("c",input);
		m.put("path","");
		
		handleObj(m);
		return output;
	}
	
	
	private Map buildNextInfoMap(Map p)
	{
		Map m = new HashMap();
		
		m.put("root",p.get("root"));
		m.put("filter",p.get("filter"));
		m.put("output",p.get("output"));
		m.put("p",p);
		
		return m;
	}
	
	
	private void filterElement(Map info) throws Exception
	{
		F filter = (F) info.get("filter");
		if(filter.f(info))
		{
			List output = (List) info.get("output");
			output.add(info);
		}
	}
	
	
	
	private void handleObj(Map info) throws Exception
	{
		filterElement(info);
		
		Object c = info.get("c");
		
		if(c instanceof List)		handleList(info);
		else if(c instanceof Set)	handleSet(info);
		else if(c instanceof Map)	handleMap(info);
		else if(c instanceof Object[])	handleArray(info);
		else if(c instanceof File)	handleFile(info);
		
		else if(c instanceof String)	handleString(info);
		else if(c instanceof Number)	handleNumber(info);
		else if(c instanceof Boolean)	handleBoolean(info);
		
		else  throw new Exception("Invalid data type: "+c.getClass().getName());
	}
	
	
	
	
	private void handleList(Map info) throws Exception
	{
		List c = (List) info.get("c");
		String path = (String) info.get("path");
		
		for(int i=0;i<c.size();i++)
		{
			Object element = c.get(i);
			Integer key = Integer.valueOf(i);
			String newPath = path+"."+key;
			
			Map m = buildNextInfoMap(info);
			m.put("path",newPath);
			m.put("key",key);
			m.put("c",element);
			
			handleObj(m);
		}
	}
	
	
	private void handleArray(Map info) throws Exception
	{
		Object[] c = (Object[]) info.get("c");
		String path = (String) info.get("path");
		
		for(int i=0;i<c.length;i++)
		{
			Object element = c[i];
			Integer key = Integer.valueOf(i);
			String newPath = path+"."+key;
			
			Map m = buildNextInfoMap(info);
			m.put("path",newPath);
			m.put("key",key);
			m.put("c",element);
			
			handleObj(m);
		}
	}
	
	
	private void handleSet(Map info) throws Exception
	{
		Set c = (Set) info.get("c");
		String path = (String) info.get("path");
		
		Iterator it = c.iterator();
		while(it.hasNext())
		{
			String key = "*";
			String newPath = path+"."+key;
			Object element = it.next();
			
			Map m = buildNextInfoMap(info);
			m.put("path",newPath);
			m.put("key",key);
			m.put("c",element);
			
			handleObj(m);
		}
	}
	
	
	private void handleMap(Map info) throws Exception
	{
		Map c = (Map) info.get("c");
		String path = (String) info.get("path");
		
		Iterator it = c.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object element = c.get(key);
			String newPath = path+"."+key;
			
			Map m = buildNextInfoMap(info);
			m.put("path",newPath);
			m.put("key",key);
			m.put("c",element);
			
			handleObj(m);
		}
	}
	
	
	private void handleFile(Map info) throws Exception
	{
		File c = (File) info.get("c");
		if(!c.isDirectory()) return;
		
		String path = (String) info.get("path");
		
		File[] ff = c.listFiles();
		if(ff!=null) for(int i=0;i<ff.length;i++)
		{
			File element = ff[i];
			String key = element.getName();
			String newPath = path+"."+key;
			
			Map m = buildNextInfoMap(info);
			m.put("path",newPath);
			m.put("key",key);
			m.put("c",element);
			
			handleObj(m);
		}
	}
	
	
	
	
	private void handleString(Map info) throws Exception
	{
	}
	
	private void handleNumber(Map info) throws Exception
	{
	}
	
	private void handleBoolean(Map info) throws Exception
	{
	}
}
