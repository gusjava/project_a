package a.entity.gus06.string.transform.tab.flatten;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141114";}
	
	public static final String DELIM = "\t";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		
		StringBuffer b = new StringBuffer();
		int number = findColumnNumber(n);
		
		for(int i=0;i<number;i++)
		{
			for(int j=0;j<n.length;j++)
			{
				String[] parts = n[j].split(DELIM,-1);
				String value = parts.length>i?parts[i]:"";
				b.append(value+"\n");
			}
			if(i<number-1) b.append("\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private int findColumnNumber(String[] n)
	{
		int number = 0;
		for(int i=0;i<n.length;i++)
		{
			String[] parts = n[i].split(DELIM,-1);
			if(number<parts.length) number = parts.length;
		}
		return number;
	}
}
