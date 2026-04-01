package a.entity.gus06.filter.string.build.rule0;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}

	
	
	public Object t(Object obj) throws Exception
	{
		String rule = (String) obj;
		return build(rule);
	}
	
	
	
	private F build(String rule) throws Exception
	{
		if(rule.equals("true")) 			return ALL;
		if(rule.equals("all")) 				return ALL;
		if(rule.equals("always"))			return ALL;
		
		if(rule.equals("false"))			return NONE;
		if(rule.equals("none"))				return NONE;
		if(rule.equals("never"))			return NONE;
		
		if(rule.equals("null"))				return NULL;
		if(rule.equals("undefined"))			return NULL;
		
		if(rule.equals("exists"))			return EXISTS;
		if(rule.equals("defined"))			return EXISTS;
		
		if(rule.equals("empty"))			return EMPTY;
		if(rule.equals("filled"))			return FILLED;
		if(rule.equals("char"))				return CHAR;
		
		if(rule.equals("lowercase"))			return LOWERCASE;
		if(rule.equals("uppercase"))			return UPPERCASE;
		if(rule.equals("number"))			return NUMBER;
		if(rule.equals("word"))				return WORD;
		
		if(rule.equals("trimed"))			return TRIMED;
		if(rule.equals("multiline"))			return MULTILINE;
		if(rule.equals("surrounded"))			return SURROUNDED;
		
		throw new Exception("Unknown rule for string filter: "+rule);
	}
	
	
	
	
	
	
	
	public final static F ALL = new F(){
		public boolean f(Object obj) throws Exception {return true;}
	};
	
	public final static F NONE = new F(){
		public boolean f(Object obj) throws Exception {return false;}
	};
	
	public final static F NULL = new F(){
		public boolean f(Object obj) throws Exception {return obj==null;}
	};
	
	public final static F EXISTS = new F(){
		public boolean f(Object obj) throws Exception {return obj!=null;}
	};
	
	public final static F EMPTY = new F(){
		public boolean f(Object obj) throws Exception {return ((String) obj).length()==0;}
	};
	
	public final static F FILLED = new F(){
		public boolean f(Object obj) throws Exception {return ((String) obj).length()>0;}
	};
	
	public final static F CHAR = new F(){
		public boolean f(Object obj) throws Exception {return ((String) obj).length()==1;}
	};
	
	
	
	
	
	public final static F UPPERCASE = new F(){
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) obj;
			return str.equals(str.toUpperCase()) && !str.equals(str.toLowerCase());
		}
	};
	
	public final static F LOWERCASE = new F(){
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) obj;
			return str.equals(str.toLowerCase()) && !str.equals(str.toUpperCase());
		}
	};

	public final static F NUMBER = new F(){
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			try{Long.parseLong((String) obj);return true;}
			catch(Exception e){return false;}
		}
	};

	public final static F WORD = new F(){
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) obj;
			for(int i=0;i<str.length();i++) if(!isLetter(str.charAt(i))) return false;
			return true;
		}
	};
	
	public final static F TRIMED = new F(){
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) obj;
			return str.trim().equals(str);
		}
	};
	
	public final static F MULTILINE = new F(){
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) obj;
			return str.contains("\n") || str.contains("\r");
		}
	};
	
	
	public final static F SURROUNDED = new F(){
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) obj;
			if(t(str,"(",")")) return true;
			if(t(str,"[","]")) return true;
			if(t(str,"{","}")) return true;
			if(t(str,"<",">")) return true;
			return false;
		}
	};
	
	
	
	
	
	

	
	private static boolean t(String s, String s1, String s2)
	{return s.startsWith(s1) && s.endsWith(s2);}
	
	
	
	private static boolean isLetter(char c)
	{
		int code = (int) c;
		return (code>96 && code<123) || (code>64 && code<91);
	}
}
