package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.handlecontent;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141002";}


	
	private Service handleSpan;
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map span = (Map) o[0];
		Map args = (Map) o[1];
		
		if(!span.containsKey("content")) throw new Exception("Content not found for span: "+span.get("value"));
		List content = (List) span.get("content");
		if(content.isEmpty()) throw new Exception("Empty content for span: "+span.get("value"));
		
		R mr = (R) span.get("main");
		Map funcs = (Map) span.get("funcs");
		Map vars = (Map) span.get("vars");
		
		if(handleSpan==null)
			handleSpan = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span");
		
		for(int i=0;i<content.size();i++)
		{
			Map s = (Map) content.get(i);
			
			s.put("main",mr);
			s.put("args",args);
			s.put("funcs",funcs);
			s.put("vars",vars);
			
			handleSpan.p(s);
		}
	}
}
