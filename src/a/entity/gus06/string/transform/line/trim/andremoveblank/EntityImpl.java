package a.entity.gus06.string.transform.line.trim.andremoveblank;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170201";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<n.length;i++)
		{
			String line = n[i].trim();
			if(valid(line)) b.append(line+DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private boolean valid(String n)
	{return !n.equals("");}
}
