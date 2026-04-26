package a.entity.gus06.string.transform.tab.add.first.clipboard;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190123";}
	
	public static final String DELIM = "\t";
	
	
	private Service clipboard;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s1 = (String) obj;
		String s2 = (String) clipboard.g();
		
		String[] n1 = s1.split("\n",-1);
		String[] n2 = s2.split("\n",-1);
		
		int max = Math.max(n1.length,n2.length);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<max;i++)
		{
			String c1 = i<n1.length ? n1[i] : "";
			String c2 = i<n2.length ? n2[i] : "";
			
			b.append(c2+DELIM+c1+"\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
