package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.perform2;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141001";}


	private Service load;
	private Service perform3;
	private Service buildArgs;


	public EntityImpl() throws Exception
	{
		load = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.load");
		perform3 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.perform3");
		buildArgs = Outside.service(this,"gus06.map.string.stringtomap.builder2.a");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String rule = (String) o[1];
		
		String[] n = rule.split(";");
		String name = n[0];
		
		String content = (String) load.t(new Object[]{mr,name});
		Map args = (Map) buildArgs(n);
		
		perform3.p(new Object[]{mr,content,args});
	}
	
	
	
	private Map buildArgs(String[] n) throws Exception
	{return Collections.unmodifiableMap((Map) buildArgs.t(n));}
}
