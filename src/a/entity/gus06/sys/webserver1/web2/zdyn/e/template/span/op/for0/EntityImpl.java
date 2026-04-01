package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.op.for0;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141002";}


	private Service formatInfo;
	private Service handleContent;
	private Service evalInt;
	
	public EntityImpl() throws Exception
	{
		formatInfo = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.formatinfo");
		handleContent = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.handlecontent");
		evalInt = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.bool.eval1.t");
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{applyFor((Map) obj);}
	
	
	
	private void applyFor(Map span) throws Exception
	{
		Map args = (Map) span.get("args");
		String rule = (String) formatInfo.t(span);
		
		int times = toInt(rule,-1);
		if(times>0)
		{
			for(int i=0;i<times;i++)
			handleContent(span,args);
			return;
		}
		
		if(rule.contains("="))
		{
			String[] n = rule.split("=",2);
			String arg = n[0];
			int[] r = analyzeRange(n[1]);
			
			int min = Math.min(r[0],r[1]);
			int max = Math.max(r[0],r[1]);
		
			for(int i=r[0];i>=min && i<=max;i+=r[2])
			{
				Map newArgs = buildArgs(args,arg,""+i);
				handleContent(span,newArgs);
			}
		}
	}
	
	
	private void handleContent(Map span, Map args) throws Exception
	{handleContent.p(new Object[]{span,args});}
	
	
	
	private int[] analyzeRange(String range)
	{
		String[] n = range.split(";",2);
		int r3 = n.length==2?toInt(n[1],1):1;
		
		String[] m = n[0].split(" ",2);
		int r1 = toInt(m[0],0);
		int r2 = toInt(m[m.length-1],0);
		
		return new int[]{r1,r2,r3};
	}
	
	
	
	
	
	private Map buildArgs(Map args, String key, String value)
	{
		Map newArgs = new HashMap(args);
		newArgs.put(key,value);
		return Collections.unmodifiableMap(newArgs);
	}
	
	
	
	private int toInt(String s, int dValue)
	{
		try
		{
			s = (String) evalInt.t(s);
			return Integer.parseInt(s);
		}
		catch(Exception e) {return dValue;}
	}
}
