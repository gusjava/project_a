package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.op.template;

import a.framework.*;
import java.util.Map;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141002";}

	private Service load;
	private Service perform;
	private Service buildArgs;
	private Service switchArgs;
	private Service formatInfo;


	public EntityImpl() throws Exception
	{
		load = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.load");
		buildArgs = Outside.service(this,"gus06.map.string.stringtomap.builder2.a");
		switchArgs = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.template.switchargs");
		formatInfo = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.formatinfo");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map span = (Map) obj;
		
		R mr = (R) span.get("main");
		Map args = (Map ) span.get("args");
		String info = (String) formatInfo.t(span);
		
		perform(mr,info,args);
	}
	
	
	
	
	
	
	
	
	private void perform(R mr, String rule, Map args0) throws Exception
	{
		String[] n = rule.split(";");
		String name = n[0];
		
		String content = (String) load.t(new Object[]{mr,name});
		Map args = buildArgs(n,args0);
		
		if(perform==null) perform = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.perform3");
		perform.p(new Object[]{mr,content,args});
	}
	
	
	
	
	private Map buildArgs(String[] n, Map args0) throws Exception
	{
		Map args = (Map) switchArgs.t(args0);
		Map newArgs = (Map) buildArgs.t(n);
		args.putAll(newArgs);
		return Collections.unmodifiableMap(args);
	}
}
