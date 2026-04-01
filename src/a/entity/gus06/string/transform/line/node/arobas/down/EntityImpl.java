package a.entity.gus06.string.transform.line.node.arobas.down;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201228";}
	
	public static final String DELIM = "\n";
	public static final String OFFSET = "@";
	public static final char CHAR_OFFSET = '@';
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		int v_min = Integer.MAX_VALUE;
		for(int i=0;i<n.length;i++)
		{
			int v = elemNb(n[i]);
			if(v>0 && v<v_min) v_min = v;
		}
		if(v_min<=1) return s;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		{
			if(n[i].startsWith(OFFSET))
			b.append(n[i].substring(1)+DELIM);
			else b.append(n[i]+DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	private int elemNb(String line)
	{
		for(int i=0;i<line.length();i++)
			if(line.charAt(i)!=CHAR_OFFSET) return i;
		return line.length();
	}
}