package a.entity.gus06.string.transform.sequence.each.same;

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
		if(nn.length==0) return "false";
		
		String n0 = nn[0];
		for(String n:nn)
		{
			if(!n.equals(n0)) return "false";
		}
		return "true";
	}
}
