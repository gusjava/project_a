package a.entity.gus06.filter.string.build.number.integer.filter1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}

	private Service findInt;
	
	public EntityImpl() throws Exception
	{
		findInt = Outside.service(this,"gus06.find.integer");
	}

	public Object t(Object obj) throws Exception
	{return new Filter((String) obj);}
	
	private class Filter implements F
	{
		private String rule;
		public Filter(String rule){this.rule = rule;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			
			int number = 0;
			try{number = toInt(obj);}
			catch(Exception e){return false;}
			
			return checkNumber(rule,number);
		}
	}
	
	private boolean checkNumber(String rule, int number) throws Exception
	{
		if(co(rule," "))
		{
			String[] n = rule.split(" ");
			for(int i=0;i<n.length;i++)
				if(checkNumber(n[i],number)) return true;
			return false;
		}
		
		if(co(rule,","))
		{
			String[] n = rule.split(",");
			if(n.length!=2) throw new Exception("Invalid integer filter rule: "+rule);
			
			String p1 = n[0];
			String p2 = n[1];
			
			if(sr(rule,"[","]")) return number>=toInt(sub1(p1)) && number<=toInt(subl(p2));
			if(sr(rule,"]","]")) return number>toInt(sub1(p1)) && number<=toInt(subl(p2));
			if(sr(rule,"[","[")) return number>=toInt(sub1(p1)) && number<toInt(subl(p2));
			if(sr(rule,"]","[")) return number>toInt(sub1(p1)) && number<toInt(subl(p2));
			
			return number>=toInt(p1) && number<=toInt(p2);
		}
		
		if(rule.equals("even")) return number%2==0;
		if(rule.equals("odd")) return number%2==1;

		if(rule.matches("[2-9]n"))
		{
			rule = rule.substring(0,rule.length()-1);
			return number%toInt(rule)==0;
		}
		if(rule.matches("[2-9]n\\+1"))
		{
			rule = rule.substring(0,rule.length()-3);
			return number%toInt(rule)==1;
		}
		if(rule.matches("[3-9]n\\+2"))
		{
			rule = rule.substring(0,rule.length()-3);
			return number%toInt(rule)==2;
		}
		if(rule.matches("[4-9]n\\+3"))
		{
			rule = rule.substring(0,rule.length()-3);
			return number%toInt(rule)==3;
		}

		if(st(rule,">=")) return number>=toInt(sub(rule,2));
		if(st(rule,"=>")) return number>=toInt(sub(rule,2));
		if(st(rule,"<=")) return number<=toInt(sub(rule,2));
		if(st(rule,"=<")) return number<=toInt(sub(rule,2));
		if(st(rule,"<>")) return number!=toInt(sub(rule,2));
		if(st(rule,"+-")) return Math.abs(number)==toInt(sub(rule,2));
		
		if(st(rule,"=")) return number==toInt(sub(rule,1));
		if(st(rule,">")) return number>toInt(sub(rule,1));
		if(st(rule,"<")) return number<toInt(sub(rule,1));
		
		
		return toInt(rule)==number;
	}
	
	private boolean sr(String s, String st, String en)
	{return st(s,st) && en(s,en);}
	
	private boolean en(String s, String en)
	{return s.endsWith(en);}
	
	private boolean st(String s, String st)
	{return s.startsWith(st);}
	
	private boolean co(String s, String co)
	{return s.contains(co);}
	
	
	private String sub(String s, int n)
	{return s.substring(n);}
	
	private String sub1(String s)
	{return s.substring(1);}
	
	private String subl(String s)
	{return s.substring(0,s.length()-1);}
	
	
	private int toInt(Object obj) throws Exception
	{
		Integer n = (Integer) findInt.t(obj);
		return n.intValue();
	}
}
