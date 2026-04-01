package a.entity.gus06.data.string.formatchecker1;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160302";}

	
	
	public boolean f(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String text = o[0];
		String format = o[1];
		
		if(format.startsWith(":"))
			return checkRegex(text,format.substring(1));
		
		if(format.startsWith("$"))
			return checkType(text,format.substring(1));
		
		return oneOfThem(text,format);
	}
	
	
	private boolean checkType(String text, String type) throws Exception
	{
		if(type.equals("int")) return isInt(text);
		if(type.equals("int+")) return isIntPos(text);
		if(type.equals("boolean")) return isBool(text);
		
		// ajouter : int-, num, alphanum, alphanum1, upper, lower, hexa
		
		throw new Exception("Unknown text type: "+type);
	}
	
	
	private boolean checkRegex(String text, String regex)
	{
		return text.matches(regex);
	}
	
	
	private boolean oneOfThem(String text, String s)
	{
		String[] nn = s.split("\\|");
		for(String n:nn) if(text.equals(n)) return true;
		return false;
	}
	
	
	
	
	private boolean isInt(String s)
	{
		try
		{
			Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException e)
		{return false;}
	}
	
	private boolean isIntPos(String s)
	{
		try
		{
			int v = Integer.parseInt(s);
			return v>=0;
		}
		catch(NumberFormatException e)
		{return false;}
	}
	
	private boolean isBool(String s)
	{
		s = s.toLowerCase();
		return s.equals("true") || s.equals("false");
	}
}