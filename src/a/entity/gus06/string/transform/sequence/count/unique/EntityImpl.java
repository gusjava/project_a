package a.entity.gus06.string.transform.sequence.count.unique;

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
			if(!set1.contains(n)) set1.add(n);
			else if(!set2.contains(n)) set2.add(n);
		}
		
		int nb = set1.size() - set2.size();
		return ""+nb;
	}
}
