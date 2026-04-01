package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.code.execute;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141007";}


	private Service buildObj;
	private Service buildVar;
	

	public EntityImpl() throws Exception
	{
		buildObj = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.build");
		buildVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map span = (Map) obj;
		
		Map vars = (Map) span.get("vars");
		String line = (String) span.get("value");
		
		if(line.contains("="))
		{
			String[] n = line.split(" *= *",2);
			String name = n[0];
			String rule = n[1];
			
			Object result = buildObj.t(new Object[]{span,rule});
			if(result!=null) vars.put(name,result);
			else vars.remove(name);
		}
		else if(line.contains(" "))
		{
			String[] n = line.split(" ",2);
			String op = n[0];
			String info = n[1];
			
			if(op.equals("p")) process_p(span,info);
		}
		else
		{
			
		}
	}
	
	
	
	
	
	private void process_p(Map span, String info) throws Exception
	{
		
		String[] n = info.split(" ",2);
		if(n.length!=2) throw new Exception("Invalid info: "+info);
		
		Map vars = (Map) span.get("vars");
		Object obj1 = buildVar.t(new Object[]{vars,n[0]});
		Object obj2 = buildVar.t(new Object[]{vars,n[1]});
		
		((P)obj1).p(obj2);
	}
}
