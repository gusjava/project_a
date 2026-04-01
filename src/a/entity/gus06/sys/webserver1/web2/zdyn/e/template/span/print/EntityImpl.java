package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.print;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141002";}



	private Service formatValue;
	
	
	public EntityImpl() throws Exception
	{formatValue = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.formatvalue");}
	
	
	public void p(Object obj) throws Exception
	{print((Map) obj);}
	
	
	
	private void print(Map span) throws Exception
	{
		R mr = (R) span.get("main");
		String value = (String) formatValue.t(span);
		
		P h = (P) mr.r("data h");
		h.p(value);
	}
}
