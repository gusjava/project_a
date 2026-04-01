package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.formatinfo2;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141030";}

	private Service formatter;
	private Service buildVar;


	public EntityImpl() throws Exception
	{
		formatter = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.format.formatter2");
		buildVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map span = (Map) obj;
		
		R mr = (R) span.get("main");
		Map args = (Map) span.get("args");
		Map vars = (Map) span.get("vars");
		String info = (String) span.get("info");
		
		Object result = buildVar.t(new Object[]{vars,info});
		if(result!=null) return result;
		
		return formatter.t(new Object[]{mr,info,args,vars});
	}
}
