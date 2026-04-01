package a.entity.gus06.string.transform.line.count.distinct_i;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160620";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split(DELIM,-1);
		
		Set set = new HashSet();
		for(String n:nn) set.add(format(n));
		
		return ""+set.size();
	}
	
	private String format(String s)
	{return s.toLowerCase();}
}
