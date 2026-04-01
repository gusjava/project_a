package a.entity.gus06.string.transform.line.clean;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230119";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		Arrays.sort(n);
		
		StringBuffer b = new StringBuffer();
		Set set = new HashSet();
		
		for(int i=0;i<n.length;i++)
		if(!set.contains(n[i]) && valid(n[i]))
		{
		    set.add(n[i]);
		    b.append(n[i]+DELIM);
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private boolean valid(String n)
	{return !n.trim().equals("");}
}