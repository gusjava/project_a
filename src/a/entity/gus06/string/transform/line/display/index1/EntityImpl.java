package a.entity.gus06.string.transform.line.display.index1;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200308";}
	
	public static final String DELIM = "\n";
	public static final String DELIM2 = "\t";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		b.append((i+1)+DELIM2+n[i]+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
