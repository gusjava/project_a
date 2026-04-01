package a.entity.gus06.string.lines.tab.setnb;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190703";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String s = (String) o[0];
		int count = ((Integer) o[1]).intValue();
		
		return setNb(s,count);
	}
	
	
	private String setNb(String s, int count)
	{
		String[] nn = s.split(DELIM,-1);
		
		int v_min = Integer.MAX_VALUE;
		
		for(String n:nn)
		{
			int v = tabNb(n);
			if(v<v_min) v_min = v;
		}
		
		StringBuffer b = new StringBuffer();
		
		if(v_min>=count)
		{
			int sub = v_min-count;
			
			for(String n:nn)
			b.append(n.substring(sub)+DELIM);
		}
		else
		{
			int add = count-v_min;
			String tab = buildTab(add);
			
			for(String n:nn)
			b.append(tab+n+DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private int tabNb(String line)
	{
		for(int i=0;i<line.length();i++)
			if(line.charAt(i)!='\t') return i;
		return line.length();
	}
	
	private String buildTab(int nb)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++) b.append("\t");
		return b.toString();
	}
}
