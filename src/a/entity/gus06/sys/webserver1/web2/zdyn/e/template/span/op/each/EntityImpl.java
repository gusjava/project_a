package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.op.each;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141007";}


	private Service formatInfo;
	private Service handleContent;
	private Service buildVar;
	
	public EntityImpl() throws Exception
	{
		formatInfo = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.formatinfo");
		handleContent = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.handlecontent");
		buildVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{applyEach((Map) obj);}
	
	
	
	private void applyEach(Map span) throws Exception
	{
		Map args = (Map) span.get("args");
		Map vars = (Map) span.get("vars");
		
		String rule = (String) formatInfo.t(span);
		String[] n = rule.split(";");
		
		String name = n[0];
		String name_key = n.length>1?n[1]:"key";
		String name_value = n.length>2?n[2]:"value";
		
		Object r = buildVar.t(new Object[]{vars,name});
		
		if(r instanceof Map)
		{
			Map map = (Map) r;
			ArrayList keys = new ArrayList(map.keySet());
			Collections.sort(keys);
			for(int i=0;i<keys.size();i++)
			{
				String key = (String) keys.get(i);
				Object value = map.get(key);
				
				vars.put(name_key,key);
				vars.put(name_value,value);
				vars.put("index",""+i);
				
				handleContent(span,args);
			}
		}
		else if(r instanceof List)
		{
			List keys = (List) r;
			for(int i=0;i<keys.size();i++)
			{
				Object key = keys.get(i);
				
				vars.put(name_key,key);
				vars.put("index",""+i);
				
				handleContent(span,args);
			}
		}
		else if(r instanceof Set)
		{
			ArrayList keys = new ArrayList((Set) r);
			Collections.sort(keys);
			for(int i=0;i<keys.size();i++)
			{
				Object key = keys.get(i);
				
				vars.put(name_key,key);
				vars.put("index",""+i);
				
				handleContent(span,args);
			}
		}
		else if(r instanceof Object[])
		{
			Object[] keys = (Object[]) r;
			for(int i=0;i<keys.length;i++)
			{
				Object key = keys[i];
				
				vars.put(name_key,key);
				vars.put("index",""+i);
				
				handleContent(span,args);
			}
		}
		else if(r instanceof String)
		{
			String key = (String) r;
			
			vars.put(name_key,key);
			vars.put("index","0");
			
			handleContent(span,args);
		}
	}
	
	
	private void handleContent(Map span, Map args) throws Exception
	{handleContent.p(new Object[]{span,args});}
}
