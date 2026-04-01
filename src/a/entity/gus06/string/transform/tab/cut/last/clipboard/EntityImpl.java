package a.entity.gus06.string.transform.tab.cut.last.clipboard;

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
		
		StringBuffer b1 = new StringBuffer();
		StringBuffer b2 = new StringBuffer();
		
		for(int i=0;i<nb;i++)
		{
			String[] parts = n[i].split(DELIM,-1);
			if(parts.length>1)
			{
				for(int j=0;j<parts.length-1;j++)
				{
					b2.append(parts[j]);
					if(j<parts.length-2) b2.append(DELIM);
				}
				b1.append(parts[parts.length-1]);
			}
			
			b1.append("\n");
			b2.append("\n");
		}
		
		clipboard.p(b1.toString());
		return b2.toString();
	}
}
