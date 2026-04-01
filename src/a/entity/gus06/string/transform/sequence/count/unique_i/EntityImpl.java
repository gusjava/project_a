package a.entity.gus06.string.transform.sequence.count.unique_i;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160620";}
	
	public static final String DELIM = ";";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split(DELIM,-1);
		
		Set set1 = new HashSet();
		Set set2 = new HashSet();
		
		for(String n:nn)
		{
			String a = format(n);
			if(!set1.contains(a)) set1.add(a);
			else if(!set2.contains(a)) set2.add(a);
		}
		
		int nb = set1.size() - set2.size();
		return ""+nb;
	}
	
	private String format(String s)
	{return s.toLowerCase();}
}
