package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.op.var;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141003";}



	private Service handleSpan;
	private Service formatInfo;
	private Service evalInt;
	private Service findVar;
	private Service emptyData;
	
	
	public EntityImpl() throws Exception
	{
		formatInfo = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.formatinfo");	
		evalInt = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.bool.eval1.t");
		findVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");
		emptyData = Outside.service(this,"gus06.data.perform.empty");
	}
	
	
	
	public void p(Object obj) throws Exception
	{applyVar((Map) obj);}
	
	
	
	private void applyVar(Map span) throws Exception
	{
		R mr = (R) span.get("main");
		Map vars = (Map) span.get("vars");
		
		String rule = (String) formatInfo.t(span);
		
		
		if(rule.startsWith("remove "))
		{
			String name = rule.split(" ",2)[1];
			if(!vars.containsKey(name)) return;
			vars.remove(name);
			return;
		}

		if(rule.startsWith("rename "))
		{
			String[] n = rule.split(" ");
			if(!vars.containsKey(n[1])) return;
			Object value = vars.get(n[1]);
			vars.remove(n[1]);
			vars.put(n[2],value);
			return;
		}

		if(rule.startsWith("alias "))
		{
			String[] n = rule.split(" ",3);
			if(n.length!=3) return;
			Object value = findVar.t(new Object[]{vars,n[2]});
			vars.put(n[1],value);
			return;
		}
				
		if(rule.startsWith("empty "))
		{
			String name = rule.split(" ",2)[1];
			if(!vars.containsKey(name)) return;
			Object value = vars.get(name);
			emptyData.p(value);
			return;
		}
		
		if(rule.startsWith("sort "))
		{
			
			String name = rule.split(" ",2)[1];
			if(!vars.containsKey(name)) return;
			List value = (List) vars.get(name);
			Collections.sort(value);
			return;
		}
		
		
		
		if(rule.endsWith("++"))
		{
			String name = rule.substring(0,rule.length()-2);
			if(!vars.containsKey(name)) return;
			String value = (String) vars.get(name);
			
			try{vars.put(name,""+(toInt(value)+1));}
			catch(NumberFormatException e){}
			return;
		}
		
		if(rule.endsWith("--"))
		{
			String name = rule.substring(0,rule.length()-2);
			if(!vars.containsKey(name)) return;
			String value = (String) vars.get(name);
			
			try{vars.put(name,""+(toInt(value)-1));}
			catch(NumberFormatException e){}
			return;
		}
		
		if(rule.contains("+="))
		{
			String[] n = rule.split("\\+=",2);
			String name = n[0];
			String delta = n[1];
			if(!vars.containsKey(name)) return;
			String value = (String) vars.get(name);
			
			try{vars.put(name,""+(toInt(value)+toInt(delta)));}
			catch(NumberFormatException e){}
			return;
		}
		
		if(rule.contains("-="))
		{
			String[] n = rule.split("-=",2);
			String name = n[0];
			String delta = n[1];
			if(!vars.containsKey(name)) return;
			String value = (String) vars.get(name);
			
			try{vars.put(name,""+(toInt(value)-toInt(delta)));}
			catch(NumberFormatException e){}
			return;
		}
		
		if(rule.contains("*="))
		{
			String[] n = rule.split("\\*=",2);
			String name = n[0];
			String delta = n[1];
			if(!vars.containsKey(name)) return;
			String value = (String) vars.get(name);
			
			try{vars.put(name,""+(toInt(value)*toInt(delta)));}
			catch(NumberFormatException e){}
			return;
		}
		
		if(rule.contains("/="))
		{
			String[] n = rule.split("\\/=",2);
			String name = n[0];
			String delta = n[1];
			if(!vars.containsKey(name)) return;
			String value = (String) vars.get(name);
			
			try{vars.put(name,""+(toInt(value)/toInt(delta)));}
			catch(NumberFormatException e){}
			return;
		}
		
		if(rule.contains("="))
		{
			String[] n = rule.split("=",2);
			
			try{vars.put(n[0],""+toInt(n[1]));}
			catch(Exception e)
			{vars.put(n[0],n[1]);}
			return;
		}
		
		Object value_ = findVar.t(new Object[]{vars,rule});
		print(mr,display(value_));
	}
	
	
	
	
	private void print(R mr, String value) throws Exception
	{
		P h = (P) mr.r("data h");
		h.p(value);
	}
	
	
	private int toInt(String s) throws Exception
	{
		s = (String) evalInt.t(s);
		return Integer.parseInt(s);
	}
	
	
	private String display(Object obj)
	{
		if(obj==null) return "null";
		if(obj instanceof String) return (String) obj;
		return "["+obj.getClass().getSimpleName()+"]";
	}
}
