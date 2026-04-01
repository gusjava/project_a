package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.op.call;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141003";}



	private Service formatInfo;
	private Service handleSpan;
	
	public EntityImpl() throws Exception
	{formatInfo = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.formatinfo");}
	
	
	
	public void p(Object obj) throws Exception
	{applyCall((Map) obj);}
	
	
	
	private void applyCall(Map span) throws Exception
	{
		R mr = (R) span.get("main");
		Map args = (Map) span.get("args");
		Map funcs = (Map) span.get("funcs");
		Map vars = (Map) span.get("vars");
		
		String rule = (String) formatInfo.t(span);
		
		String[] n = rule.split(";",2);
		String name = n[0];
		String params_v = n.length==2?n[1]:null;
		
		if(!funcs.containsKey(name))
		{notFound(mr,name);return;}
		
		
		Map function = (Map) funcs.get(name);
		List content = (List) function.get("content");
		Map newArgs = buildArgs(args,params_v,function);
		
		if(handleSpan==null)
			handleSpan = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span");
		
		for(int i=0;i<content.size();i++)
		{
			Map s = (Map) content.get(i);
			
			s.put("main",mr);
			s.put("args",newArgs);
			s.put("funcs",funcs);
			s.put("vars",vars);
			
			handleSpan.p(s);
		}
	}
	
	
	
	
	private void notFound(R mr, String name) throws Exception
	{
		P h = (P) mr.r("data h");
		h.p("<p>Function not found: "+name+"</p>");
	}
	
	
	
	private Map buildArgs(Map args, String params_v, Map function)
	{
		if(params_v==null) return args;
		if(!function.containsKey("params")) return args;
		
		String params_k = (String) function.get("params");
		
		String[] v = params_v.split(";");
		String[] k = params_k.split(";");
		
		int n = Math.min(v.length,k.length);
		Map newArgs = new HashMap(args);
		for(int i=0;i<n;i++)
		newArgs.put(k[i],v[i]);
		
		return Collections.unmodifiableMap(newArgs);
	}
}
