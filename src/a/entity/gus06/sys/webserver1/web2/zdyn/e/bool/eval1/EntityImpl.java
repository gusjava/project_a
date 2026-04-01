package a.entity.gus06.sys.webserver1.web2.zdyn.e.bool.eval1;

import a.framework.*;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20141003";}


	private Service evalInt;


	public EntityImpl() throws Exception
	{evalInt = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.bool.eval1.t");}
	
	
	public Object t(Object obj) throws Exception
	{return f(obj)?"true":"false";}
	
	
	public boolean f(Object obj) throws Exception
	{return eval((String) obj);}
	
	
	
	private boolean eval(String s) throws Exception
	{
		if(s.startsWith("!")) return !eval(s.substring(1));
		if(s.toLowerCase().equals("true")) return true;
		
		if(s.contains("!=")) return !equals(s.split("!=",2));
		if(s.contains("<>")) return !equals(s.split("<>",2));
		if(s.contains(">=")) return sup0(s.split(">=",2));
		if(s.contains("<=")) return inf0(s.split("<=",2));
		
		if(s.contains(">")) return sup1(s.split(">",2));
		if(s.contains("<")) return inf1(s.split("<",2));
		if(s.contains("=")) return equals(s.split("="));
		
		return false;
	}
	
	
	
	
	private boolean equals(String[] n)
	{return n[0].equals(n[1]);}
	
	
	private boolean sup0(String[] n) throws Exception
	{
		try{return toInt(n[0]) >= toInt(n[1]);}
		catch(NumberFormatException e){}
		return false;
	}
	
	private boolean sup1(String[] n) throws Exception
	{
		try{return toInt(n[0]) > toInt(n[1]);}
		catch(NumberFormatException e){}
		return false;
	}
	
	private boolean inf0(String[] n) throws Exception
	{
		try{return toInt(n[0]) <= toInt(n[1]);}
		catch(NumberFormatException e){}
		return false;
	}
	
	private boolean inf1(String[] n) throws Exception
	{
		try{return toInt(n[0]) < toInt(n[1]);}
		catch(NumberFormatException e){}
		return false;
	}
	
	
	
	private int toInt(String s) throws Exception
	{
		s = (String) evalInt.t(s);
		return Integer.parseInt(s);
	}
}
