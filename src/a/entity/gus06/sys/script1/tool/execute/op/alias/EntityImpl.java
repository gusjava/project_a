package a.entity.gus06.sys.script1.tool.execute.op.alias;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150906";}


	private Service getAlias;

	public EntityImpl() throws Exception
	{
		getAlias = Outside.service(this,"gus06.sys.script1.access.context.alias1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		String line = (String) o[1];
		
		Map alias = (Map) getAlias.t(context);
		setAlias(alias,line);
	}
	
	private void setAlias(Map m, String s) throws Exception
	{
		String[] n = s.split("=",2);
		if(n.length!=2) throw new Exception("Invalid alias rule: "+s);
		m.put(n[0],n[1]);
	}
}
