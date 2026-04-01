package a.entity.gus06.string.transform.line.display.occ_n;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200205";}
	
	public static final String DELIM = "\n";
	public static final String DELIM2 = "\t";
	
	
	private Service normalize;
	
	public EntityImpl() throws Exception
	{normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		Map map = new HashMap();
		for(int i=0;i<n.length;i++)
		increase(map,n[i]);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		{
			String line = n[i];
			Integer nbOcc = (Integer) map.get(format(line));
			b.append(nbOcc.intValue()+DELIM2+line+DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private void increase(Map map, String key) throws Exception
	{
		key = format(key);
		if(!map.containsKey(key))
		{map.put(key,Integer.valueOf(1));return;}
		
		Integer n = (Integer)map.get(key);
		map.put(key,Integer.valueOf(n.intValue()+1));
	}
	
	private String format(String s) throws Exception
	{return (String) normalize.t(s);}
}
