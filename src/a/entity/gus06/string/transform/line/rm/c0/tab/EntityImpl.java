package a.entity.gus06.string.transform.line.rm.c0.tab;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250728";}
	
	public static final String DELIM = "\n";
	public static final String OFFSET = "\t";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		{
			if(n[i].startsWith(OFFSET)) b.append(rm(n[i])+DELIM);
			else b.append(n[i]+DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private String rm(String line)
	{
		if(line.equals("")) return "";
		return line.substring(1);
	}
}