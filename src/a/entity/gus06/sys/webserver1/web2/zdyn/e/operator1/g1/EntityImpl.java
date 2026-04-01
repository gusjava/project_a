package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator1.g1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141009";}

	private Service build;

	public EntityImpl() throws Exception
	{build = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		
		T tran = (T) build(info);
		return tran.t(mr);
	}
	
	
	private Object build(String info) throws Exception
	{return build.t(new Object[]{new HashMap(),info});}
}
