package a.entity.gus06.convert.stringtolong.duration;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180118";}



	
	public Object t(Object obj) throws Exception
	{
		return Long.valueOf(stringToLong((String) obj));
	}
	
	/*
	* Il faut ameliorer pour prendre en compte les formats suivants :
	* ...j ...h ...m ...s ...ms
	*/
	
	private long stringToLong(String s) throws Exception
	{
		s = s.replace(" ","").toLowerCase();
		
		if(s.endsWith("ms"))
		{
			s = s.substring(0,s.length()-2);
			return Long.parseLong(s);
		}
		if(s.endsWith("s"))
		{
			s = s.substring(0,s.length()-1);
			return Long.parseLong(s) * 1000;
		}
		if(s.endsWith("min"))
		{
			s = s.substring(0,s.length()-3);
			return Long.parseLong(s) * 60000;
		}
		if(s.endsWith("m"))
		{
			s = s.substring(0,s.length()-1);
			return Long.parseLong(s) * 60000;
		}
		if(s.endsWith("h"))
		{
			s = s.substring(0,s.length()-1);
			return Long.parseLong(s) * 3600000;
		}
		if(s.endsWith("j"))
		{
			s = s.substring(0,s.length()-1);
			return Long.parseLong(s) * 86400000;
		}
		if(s.endsWith("d"))
		{
			s = s.substring(0,s.length()-1);
			return Long.parseLong(s) * 86400000;
		}
		
		return Long.parseLong(s);
	}
}
