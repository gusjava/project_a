package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.op.code;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141007";}


	private Service executeCode;


	public EntityImpl() throws Exception
	{executeCode = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.execute");}

	
	
	public void p(Object obj) throws Exception
	{applyCode((Map) obj);}
	
	
	
	
	private void applyCode(Map span) throws Exception
	{
		R mr = (R) span.get("main");
		Map args = (Map) span.get("args");
		Map vars = (Map) span.get("vars");
		
		List content = (List) span.get("content");
		
		for(int i=0;i<content.size();i++)
		{
			Map s = (Map) content.get(i);
			if(isEndSpan(s)) return;
			
			String type = (String) s.get("type");
			if(!type.equals("text")) throw new Exception("Invalid code content line: "+s.get("value"));
			
			s.put("main",mr);
			s.put("args",args);
			s.put("vars",vars);
			
			executeCode.p(s);
		}
	}
	
	
	
	
	
	
	private boolean isEndSpan(Map span)
	{return span.containsKey("name") && span.get("name").equals("end");}
}
