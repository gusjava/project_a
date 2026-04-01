package a.entity.gus06.data.perform.lower;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160818";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List) return perform((List) obj);
		if(obj instanceof Set) return perform((Set) obj);
		if(obj instanceof Map) return perform((Map) obj);
		
		if(obj instanceof Object[][]) return perform((Object[][]) obj);
		if(obj instanceof Object[]) return perform((Object[]) obj);
		
		if(obj instanceof String) return perform((String) obj);
		if(obj instanceof Number) return perform(""+obj);
		if(obj instanceof Boolean) return perform(""+obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String perform(String s)
	{
		return s.toLowerCase();
	}
	
	private String[] perform(Object[] a)
	{
		String[] a1 = new String[a.length];
		for(int i=0;i<a1.length;i++) a1[i] = perform(""+a[i]);
		return a1;
	}
	
	private String[][] perform(Object[][] a)
	{
		int nb1 = a.length;
		int nb2 = nb1>0 ? a[0].length : 0;
		
		String[][] a1 = new String[nb1][nb2];
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		a1[i][j] = perform(""+a[i][j]);
		
		return a1;
	}
	
	private List perform(List l)
	{
		List l1 = new ArrayList();
		for(Object o:l) l1.add(perform(""+o));
		return l1;
	}
	
	private Set perform(Set s)
	{
		Set s1 = new HashSet();
		for(Object o:s) s1.add(perform(""+o));
		return s1;
	}
	
	private Map perform(Map m)
	{
		Map m1 = new HashMap();
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = m.get(key);
			m1.put(perform(""+key),perform(""+value));
		}
		return m1;
	}
}
