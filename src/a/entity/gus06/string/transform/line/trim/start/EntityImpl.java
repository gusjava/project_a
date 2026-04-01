package a.entity.gus06.string.transform.line.trim.start;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160510";}
	public static final String DELIM = "\n";


	private Service trimStart;
	
	public EntityImpl() throws Exception
	{
		trimStart = Outside.service(this,"gus06.string.transform.str.trim.start");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<n.length;i++)
		b.append(trim(n[i])+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private String trim(String s) throws Exception
	{return (String) trimStart.t(s);}
}
