package a.entity.gus06.sys.cmd1.builder.data;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150626";}


	private Service toObj;


	public EntityImpl() throws Exception
	{toObj = Outside.service(this,"gus06.sys.cmd1.builder.object");}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		String[] n = s.split(" ");
		Object[] o = new Object[n.length];
		
		for(int i=0;i<n.length;i++) o[i] = toObj.t(n[i]);
		return o;
	}
}
