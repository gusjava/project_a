package a.entity.gus06.sys.parser1.engine1.impl.impl1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151023";}
	
	private Service engine1;
	private Service defaultParser;
	private Service formatCurly;


	public EntityImpl() throws Exception
	{
		engine1 = Outside.service(this,"gus06.sys.parser1.engine1.cache");
		defaultParser = Outside.service(this,"gus06.sys.parser1.engine1.impl.brackets.curly");
		formatCurly = Outside.service(this,"gus06.string.transform.format.brackets.curly");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		int n = s.indexOf("\n");
		
		if(n==-1) return defaultParser.t(s);
		
		String[] k = s.split("\n",2);
		String s1 = k[0];
		String s2 = k[1];
		
		if(s1.equals("@@")) return defaultParser.t(s2);
		if(s1.equals("@")) return defaultParser.t(s2);
		
		if(s1.startsWith("@@")) return parseCustom1(s1.substring(2),s2);
		if(s1.startsWith("@")) return parseCustom2(s1.substring(1),s2);
		
		return defaultParser.t(s);
	}
	
	
	
	
	private Object parseCustom1(String rule, String text) throws Exception
	{
		int n = rule.length();
		if(n==1 || n==2 || n==3) return getParser(rule).t(text);
		throw new Exception("Invalid syntax rule: "+rule);
	}
	
	
	
	private Object parseCustom2(String rule, String text) throws Exception
	{
		String s1 = "{"+format(rule)+"}"+format(text);
		return defaultParser.t(s1);
	}
	
	
	
	
	
	private T getParser(String info) throws Exception
	{return (T) engine1.t(info);}
	
	private String format(String s) throws Exception
	{return (String) formatCurly.t(s);}
}
