package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.t;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141007";}


	private Service opT;
	
	public EntityImpl() throws Exception
	{opT = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.t");}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map span = (Map) o[0];
		String info = (String) o[1];
		
		Object main = span.get("main");
		Object args = span.get("args");
		Object vars = span.get("vars");
		return opT.t(new Object[]{main,info,args,vars});
	}
}
