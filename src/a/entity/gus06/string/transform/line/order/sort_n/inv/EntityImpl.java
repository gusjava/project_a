package a.entity.gus06.string.transform.line.order.sort_n.inv;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160517";}
	
	public static final String DELIM = "\n";
	
	
	
	private Service normalize;
	
	public EntityImpl() throws Exception
	{normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");}


	private String normalize(String s)
	{
		try{return (String) normalize.t(s);}
		catch(Exception e){Outside.err(this,"normalize(String)",e);}
		return s;
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		Arrays.sort(n,new Comparator1());
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		b.append(n[i]+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	
	private class Comparator1 implements Comparator
	{
		private Map cache = new HashMap();
		
		public int compare(Object o1, Object o2)
		{return get(o1).compareTo(get(o2));}
		
		
		private String get(Object o)
		{
			if(!cache.containsKey(o)) cache.put(o,format((String) o));
			return (String) cache.get(o);
		}
		
		private String format(String s)
		{
			StringBuffer b = new StringBuffer(normalize(s));
			return b.reverse().toString();
		}
	}
	
}
