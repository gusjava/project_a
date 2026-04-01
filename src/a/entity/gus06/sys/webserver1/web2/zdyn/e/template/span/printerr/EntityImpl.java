package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.printerr;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141104";}


	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map span = (Map) o[0];
		Exception err = (Exception) o[1];
		
		print(span,err);
	}
	
	
	
	private void print(Map span, Exception err) throws Exception
	{
		R mr = (R) span.get("main");
		Map args = (Map) span.get("args");
		
		P h = (P) mr.r("data h");
		
		h.p("<div style=\"color: red;\">");
		
		h.p("<p>An error occured inside span:</p>");
		h.p("<ul>");
		
		h.p("<li><b>Span:</b> "+span.get("value")+"</li>");
		h.p("<li><b>Exception:</b> "+err.toString()+"</li>");
		
		h.p("<li><b>Args:</b></li>");
		printMap(h,args);
		
		h.p("<li><b>Stack trace:</b></li>");
		printStackTrace(h,err);
		
		h.p("</ul>");
		h.p("</div>");
	}
	
	
	
	private void printMap(P h, Map map) throws Exception
	{
		ArrayList keys = new ArrayList(map.keySet());
		h.p("<ul>");
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			String value = (String) map.get(key);
			h.p("<li>"+key+"="+value+"</li>");
		}
		h.p("</ul>");
	}
	
	
	
	private void printStackTrace(P h, Exception err) throws Exception
	{
		StackTraceElement[] ste_ = err.getStackTrace();
		h.p("<ul>");
		for(StackTraceElement ste:ste_)
			h.p("<li>"+toString(ste)+"</li>");
		h.p("</ul>");
	}
	
	
	
	
	private String toString(StackTraceElement ste)
	{
		if(ste==null) return "null";
		return ste.getClassName()+"@"+ste.getMethodName()+" ("+ste.getFileName()+":"+ste.getLineNumber()+")";
	}
}
