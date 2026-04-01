package a.entity.gus06.string.transform.indent.convert.tabtospace3;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201223";}
	
	public static final String DELIM = "\n";
	
	public static final String INDENT1 = "\t";
	public static final String INDENT2 = "   ";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++) handleLine(b,n[i]);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	private void handleLine(StringBuffer b, String line)
	{
		while(line.startsWith(INDENT1))
		{
			line = line.substring(INDENT1.length());
			b.append(INDENT2);
		}
		b.append(line);
		b.append(DELIM);
	}
}