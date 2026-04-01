package a.entity.gus06.data.perform.join1;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160324";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		String glue = (String) o[1];
		
		if(input instanceof Object[])	return join((Object[]) input,glue);
		if(input instanceof List)	return join((List) input,glue);
		if(input instanceof Map)	return join((Map) input,glue);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	private String join(Object[] s, String glue)
	{
		StringBuffer b = new StringBuffer();
		int n = s.length;
		
		for(int i=0;i<n;i++)
		append(b,s[i],glue);
		
		return b.toString();
	}
	
	
	private String join(List l, String glue)
	{
		StringBuffer b = new StringBuffer();
		int n = l.size();
		
		for(int i=0;i<n;i++)
		append(b,l.get(i),glue);
		
		return b.toString();
	}
	
	
	private String join(Map m, String glue)
	{
		StringBuffer b = new StringBuffer();
		List keys = new ArrayList(m.keySet());
		Collections.sort(keys);
		int n = keys.size();
		
		for(int i=0;i<n;i++)
		{
			Object key = keys.get(i);
			Object value = m.get(key);
			
			String s = empty(value)?null:key+"="+value;
			append(b,s,glue);
		}
		return b.toString();
	}
	
	
	
	private void append(StringBuffer b, Object s, String glue)
	{
		if(empty(s)) return;
		if(b.length()>0) b.append(glue);
		b.append(s);
	}
	
	private boolean empty(Object s)
	{return s==null || s.equals("");}
}
