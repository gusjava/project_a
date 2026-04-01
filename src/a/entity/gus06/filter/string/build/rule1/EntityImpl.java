package a.entity.gus06.filter.string.build.rule1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}


	private Service buildF0;
	private Service buildF1a;
	
	private Service filter_seq_allofthem;
	private Service filter_seq_oneofthem;
	
	
	
	public EntityImpl() throws Exception
	{
		buildF0 = Outside.service(this,"gus06.filter.string.build.rule0");
		buildF1a = Outside.service(this,"gus06.filter.string.build.rule1.a");
		
		filter_seq_allofthem = Outside.service(this,"gus06.filter.string.transform.sequence.allofthem");
		filter_seq_oneofthem = Outside.service(this,"gus06.filter.string.transform.sequence.oneofthem");
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String rule = (String) obj;
		return build(rule);
	}
	
	
	
	
	private F build(String rule) throws Exception
	{
		if(rule.startsWith("!")) return inv(build(rule.substring(1)));
		if(rule.startsWith("[1]")) return sequence_oneOfThem(build(rule.substring(3)));
		if(rule.startsWith("[n]")) return sequence_allOfThem(build(rule.substring(3)));
		if(!rule.contains(":")) return buildF0(rule);
		
		String[] n = rule.split(":",2);
		if(n.length!=2) throw new Exception("Invalid rule: "+rule);

		if(n[0].equals("apply")) return build(n[1]);
		return buildF1a(n);
	}
	
	
	
	
	private F buildF0(String rule) throws Exception
	{return (F) buildF0.t(rule);}
	
	private F buildF1a(String[] n) throws Exception
	{return (F) buildF1a.t(n);}
	
	private F sequence_allOfThem(F f) throws Exception
	{return (F)filter_seq_allofthem.t(f);}
	
	private F sequence_oneOfThem(F f) throws Exception
	{return (F)filter_seq_oneofthem.t(f);}
	
	
	private F inv(F f)
	{return new NotF(f);}
	
	
	private class NotF implements F
	{
		private F filter;
		public NotF(F filter) {this.filter = filter;}
		public boolean f(Object obj) throws Exception
		{return !filter.f(obj);}
	}
}
