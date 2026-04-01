package a.entity.gus06.string.transform.line.kp.common.longestpart;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}
	
	public static final String DELIM = "\n";


	private Service findCommon;
	
	public EntityImpl() throws Exception
	{findCommon = Outside.service(this,"gus06.data.compare.string.common.longest1");}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		String common = (String) findCommon.t(n);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		b.append(common+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
