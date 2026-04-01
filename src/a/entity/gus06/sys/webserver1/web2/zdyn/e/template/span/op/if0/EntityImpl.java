package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.op.if0;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141002";}


	private Service formatInfo;
	private Service eval;
	
	public EntityImpl() throws Exception
	{
		formatInfo = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.formatinfo");
		eval = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.bool.eval1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map span = (Map) obj;
		List content = (List) span.get("content");
		String info = (String) formatInfo.t(span);
		
		int elsePos = findElsePos(content);
		
		if(eval.f(info))
		{
			for(int i=0;i<elsePos;i++)
			handleSpan(span,(Map)content.get(i));
		}
		else
		{
			for(int i=elsePos+1;i<content.size();i++)
			handleSpan(span,(Map)content.get(i));
		}
	}
	
	
	
	private Service handleSpan;
	
	private void handleSpan(Map parent, Map span) throws Exception
	{
		R mr = (R) parent.get("main");
		Map args = (Map) parent.get("args");
		Map funcs = (Map) parent.get("funcs");
		Map vars = (Map) parent.get("vars");
		
		span.put("main",mr);
		span.put("args",args);
		span.put("funcs",funcs);
		span.put("vars",vars);
		
		if(handleSpan==null)
			handleSpan = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span");
		handleSpan.p(span);
	}
	
	
	
	private int findElsePos(List content)
	{
		for(int i=0;i<content.size();i++)
		{
			Map span = (Map) content.get(i);
			if(isElseSpan(span)) return i;
		}
		return content.size();
	}
	
	private boolean isElseSpan(Map span)
	{return span.containsKey("name") && span.get("name").equals("else");}
}
