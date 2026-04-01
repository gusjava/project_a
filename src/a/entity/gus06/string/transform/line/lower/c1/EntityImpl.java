package a.entity.gus06.string.transform.line.lower.c1;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210722";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<n.length;i++)
		b.append(lowerC1(n[i])+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private String lowerC1(String s)
	{
		if(s.equals("")) return s;
		int n = s.length();
		return s.substring(0,n-1) + s.substring(n-1).toLowerCase();
	}
}