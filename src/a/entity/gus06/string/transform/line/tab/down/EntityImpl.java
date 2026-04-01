package a.entity.gus06.string.transform.line.tab.down;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160509";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split(DELIM,-1);
		
		for(String n:nn)
		if(!n.startsWith("\t")) return s;
		
		StringBuffer b = new StringBuffer();
		
		for(String n:nn)
		b.append(n.substring(1)+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}