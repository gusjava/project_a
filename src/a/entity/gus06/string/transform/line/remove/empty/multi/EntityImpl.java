package a.entity.gus06.string.transform.line.remove.empty.multi;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151024";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		
		int count = 0;
		
		for(int i=0;i<n.length;i++)
		{
			if(!valid(n[i])) count++;
			else count = 0;
			
			if(count<2) b.append(n[i]+DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private boolean valid(String n)
	{return n.equals("");}
}
