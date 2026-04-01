package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator2.g1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141001";}

	private Service build;

	public EntityImpl() throws Exception
	{build = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		Map args = (Map) o[2];
		Map vars = (Map) o[3];
		
		T tran = (T) build(vars,info);
		return tran.t(mr);
	}
	
	
	private Object build(Map vars, String info) throws Exception
	{return build.t(new Object[]{vars,info});}
}
