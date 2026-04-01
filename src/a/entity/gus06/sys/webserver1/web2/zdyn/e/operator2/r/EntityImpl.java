package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator2.r;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141010";}

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
		
		if(!info.contains(" "))
			throw new Exception("Invalid info: "+info);
		
		String[] n = info.split(" ",2);
		String part1 = n[0];
		String part2 = n[1];
		
		R reg = (R) build(vars,part1);
		return reg.r(part2);
	}
	
	
	
	private Object build(Map vars, String info) throws Exception
	{return build.t(new Object[]{vars,info});}
}
