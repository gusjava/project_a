package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.op.func;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141003";}



	private Service formatInfo;
	
	public EntityImpl() throws Exception
	{formatInfo = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.formatinfo");}
	
	
	
	public void p(Object obj) throws Exception
	{applyFunc((Map) obj);}
	
	
	
	private void applyFunc(Map span) throws Exception
	{
		Map funcs = (Map) span.get("funcs");
		String rule = (String) formatInfo.t(span);
		List content = (List) span.get("content");
		
		String[] n = rule.split(";",2);
		String name = n[0];
		String params = n.length==2?n[1]:null;
		
		Map function = new HashMap();
		function.put("name",name);
		function.put("content",content);
		if(params!=null) function.put("params",params);
		
		funcs.put(name,function);
	}
}
