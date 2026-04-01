package a.entity.gus06.string.transform.tab.copy.first.clipboard;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190218";}
	
	public static final String DELIM = "\t";
	
	
	private Service clipboard;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		int nb = n.length;
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<nb;i++)
		{
			String[] parts = n[i].split(DELIM,-1);
			String c = parts.length>0 ? parts[0] : "";
			b.append(c+"\n");
		}
		
		clipboard.p(b.toString());
		return s;
	}
}
