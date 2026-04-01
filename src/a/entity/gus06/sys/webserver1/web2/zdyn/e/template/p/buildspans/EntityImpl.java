package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.p.buildspans;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141002";}

	public static final String[] BLOC_NAMES = new String[] {
		"for","each","if","while","func","ignore","code"
	};
	
	
	
	public Object t(Object obj) throws Exception
	{
		List lines = new ArrayList((List) obj);
		List spans = new ArrayList();
		
		while(!lines.isEmpty())
		{spans.add(buildSpan(lines));}
		
		return spans;
	}
	
	
	
	
	private Map buildSpan(List lines)
	{
		Map span = new HashMap();
		String line = (String) lines.remove(0);
		boolean isOp = isOperator(line);
		
		span.put("type",isOp?"operator":"text");
		span.put("value",line);
		
		if(isOp)
		{
			String[] n = line.substring(1,line.length()-1).split(":",2);
			List c = buildContent(lines,n[0]);
			
			span.put("name",n[0]);
			if(n.length==2) span.put("info",n[1]);
			if(c!=null) span.put("content",c);
		}
		return span;
	}
	
	
	
	private List buildContent(List lines, String name)
	{
		List c = new ArrayList();
		if(!isBlocSpanName(name)) return null;
		
		Map span = new HashMap();
		while(!lines.isEmpty() && !isEndSpan(span))
		{
			span = buildSpan(lines);
			c.add(span);
		}
		return Collections.unmodifiableList(c);
	}
	
	
	
	
	private boolean isOperator(String line)
	{
		int l = line.length();
		if(l<3) return false;
		return line.charAt(0)=='{' && line.charAt(l-1)=='}';
	}
	
	private boolean isBlocSpanName(String name)
	{
		for(String s:BLOC_NAMES) if(name.equals(s)) return true;
		return false;
	}
	
	private boolean isEndSpan(Map span)
	{return span.containsKey("name") && span.get("name").equals("end");}
}
