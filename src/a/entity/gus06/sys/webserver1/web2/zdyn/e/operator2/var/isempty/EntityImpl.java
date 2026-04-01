package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator2.var.isempty;

import a.framework.*;
import java.util.Map;
import java.util.Collection;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141021";}


	private Service findVar;
	private Service isEmpty;
	
	public EntityImpl() throws Exception
	{
		findVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");
		isEmpty = Outside.service(this,"gus06.data.filter.isempty");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		Map args = (Map) o[2];
		Map vars = (Map) o[3];
		
		return get(vars,info);
	}
	
	
	
	private String get(Map vars, String info) throws Exception
	{
		Object result = findVar.t(new Object[]{vars,info});
		if(result==null) throw new Exception("Invalid null result");
		return isEmpty(result);
	}
	
	private String isEmpty(Object obj) throws Exception
	{return ""+isEmpty.f(obj);}
}
