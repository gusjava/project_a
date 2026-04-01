package a.entity.gus06.string.transform.tab.order.permute.inv;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}
	
	public static final String DELIM = "\t";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		{
			String[] parts = n[i].split(DELIM,-1);
			if(parts.length==1) b.append(parts[0]+"\n");
			else
			{
				for(int j=1;j<parts.length;j++)
				b.append(parts[j]+DELIM);
				b.append(parts[0]);
				b.append("\n");
			}
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
