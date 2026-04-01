package a.entity.gus06.string.transform.line.upper.c01;

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
		b.append(upperC01(n[i])+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private String upperC01(String s)
	{
		if(s.length()<3) return l(s);
		int n = s.length();
		return l(s.substring(0,1)) + s.substring(1,n-1) + l(s.substring(n-1));
	}
	
	private String l(String s)
	{return s.toUpperCase();}
}