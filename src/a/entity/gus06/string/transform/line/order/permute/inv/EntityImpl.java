package a.entity.gus06.string.transform.line.order.permute.inv;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141105";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=1;i<n.length;i++)
		b.append(n[i]+DELIM);
		b.append(n[0]);
		
		return b.toString();
	}
}
