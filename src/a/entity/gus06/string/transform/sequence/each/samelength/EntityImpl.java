package a.entity.gus06.string.transform.sequence.each.samelength;

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
		
		int l0 = nn[0].length();
		for(String n:nn)
		{
			int l = n.length();
			if(l!=l0) return "false";
		}
		return "true";
	}
}
