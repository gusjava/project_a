package a.entity.gus06.string.transform.sequence.count.empty;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150929";}
	
	public static final String DELIM = ";";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split(DELIM,-1);
		
		int nb = 0;
		for(String n:nn) if(valid(n)) nb++;
		
		return ""+nb;
	}
	
	private boolean valid(String n)
	{return n.equals("");}
}
