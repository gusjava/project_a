package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.perform3;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141001";}


	private Service splitToLines;
	private Service buildSpans;
	private Service handleSpan;


	public EntityImpl() throws Exception
	{
		splitToLines = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.p.splittolines");
		buildSpans = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.p.buildspans");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String content = (String) o[1];
		Map args = (Map) o[2];
		
		List lines = (List) splitToLines.t(content);
		List spans = (List) buildSpans.t(lines);
		Map funcs = new HashMap();
		Map vars = new HashMap();
		
		vars.put("_mr",mr);
		vars.put("_vars",vars);
		vars.put("_spans",spans);
		vars.put("_args",args);
		vars.put("_funcs",funcs);
		
		
		
		if(handleSpan==null)
			handleSpan = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span");
		
		for(int i=0;i<spans.size();i++)
		{
			Map span = (Map) spans.get(i);
			
			span.put("main",mr);
			span.put("args",args);
			span.put("funcs",funcs);
			span.put("vars",vars);
			
			handleSpan.p(span);
		}
	}
}
