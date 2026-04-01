package a.entity.gus06.string.extract.extract2.pattern;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190426";}


	private Service quote;

	public EntityImpl() throws Exception
	{quote = Outside.service(this,"gus06.string.transform.regexp.lazywhite");}

	private String quote(String s) throws Exception
	{return (String) quote.t(s);}


	
	
	public Object t(Object obj) throws Exception
	{
		String rule = (String) obj;
		if(rule.contains("***")) return build3(rule);
		if(rule.contains("**")) return build2(rule);
		if(rule.contains("*")) return build1(rule);
		
		throw new Exception("Invalid rule: "+rule);
	}
	
	
	private String build1(String rule) throws Exception
	{
		String[] n = rule.split("\\*");
		
		if(n.length!=2) throw new Exception("Invalid rule: "+rule);
		if(n[1].length()==0) throw new Exception("Invalid rule: "+rule);
		
		String s1 = ""+n[1].charAt(0);
		return quote(n[0])+"([^"+quote(s1)+"]+)"+quote(n[1]);
	}
	
	
	private String build2(String rule) throws Exception
	{
		String[] n = rule.split("\\*\\*");
		
		if(n.length!=2) throw new Exception("Invalid rule: "+rule);
		if(n[1].length()==0) throw new Exception("Invalid rule: "+rule);
		
		return quote(n[0])+"(.+?)"+quote(n[1]);
	}
	
	
	private String build3(String rule) throws Exception
	{
		String[] n = rule.split("\\*\\*\\*");
		
		if(n.length!=2) throw new Exception("Invalid rule: "+rule);
		if(n[1].length()==0) throw new Exception("Invalid rule: "+rule);
		
		return quote(n[0])+"(.+)"+quote(n[1]);
	}
}