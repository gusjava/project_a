package a.entity.gus06.sys.script1.tool.execute.params.handler2.a;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160221";}


	private Service getParams;
	private Service buildData;
	
	public EntityImpl() throws Exception
	{
		getParams = Outside.service(this,"gus06.sys.script1.access.tag.params0");
		buildData = Outside.service(this,"gus06.sys.script1.tool.execute.params.handler2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object context = o[0];
		Object tag = o[1];
		
		Object params = getParams.t(tag);
		return buildData.t(new Object[]{context,params});
	}
}
