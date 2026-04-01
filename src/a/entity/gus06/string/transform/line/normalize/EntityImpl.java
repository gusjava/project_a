package a.entity.gus06.string.transform.line.normalize;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151102";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<n.length;i++)
		{
			String line = n[i].trim();
			if(!line.equals("")) b.append(line+DELIM);
		}
		return b.toString();
	}
}
