package a.entity.gus06.string.lines.tab.count;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190703";}


	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split(DELIM,-1);
		
		int v_min = Integer.MAX_VALUE;
		
		for(String n:nn)
		{
			int v = tabNb(n);
			if(v<v_min) v_min = v;
		}
		return v_min;
	}
	
	
	
	private int tabNb(String line)
	{
		for(int i=0;i<line.length();i++)
			if(line.charAt(i)!='\t') return i;
		return line.length();
	}
}
