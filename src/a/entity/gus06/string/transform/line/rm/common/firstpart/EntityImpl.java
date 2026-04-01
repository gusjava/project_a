package a.entity.gus06.string.transform.line.rm.common.firstpart;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151105";}
	
	public static final String DELIM = "\n";


	private Service findCommon;
	
	public EntityImpl() throws Exception
	{findCommon = Outside.service(this,"gus06.data.compare.string.common.firstpart");}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		String common = (String) findCommon.t(n);
		if(common.equals("")) return s;
		int length = common.length();
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		{
			String line = n[i].substring(length);
			b.append(line+DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
