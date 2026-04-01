package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.var;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141007";}


	private Service findVar;
	
	public EntityImpl() throws Exception
	{findVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map span = (Map) o[0];
		String info = (String) o[1];
		
		Map vars = (Map) span.get("vars");
		return findVar.t(new Object[]{vars,info});
	}
}
