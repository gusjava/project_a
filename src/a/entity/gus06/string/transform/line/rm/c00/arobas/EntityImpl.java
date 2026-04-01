package a.entity.gus06.string.transform.line.rm.c00.arobas;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191112";}
	
	public static final String DELIM = "\n";
	public static final String OFFSET = "@";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		b.append(rm(n[i])+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private String rm(String line)
	{
		while(line.startsWith(OFFSET))
		line = line.substring(1);
		return line;
	}
}
