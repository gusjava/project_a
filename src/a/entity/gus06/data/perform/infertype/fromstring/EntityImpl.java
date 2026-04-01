package a.entity.gus06.data.perform.infertype.fromstring;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180213";}

	
	public Object t(Object obj) throws Exception
	{return inferFromString((String) obj);}
	
	
	private Object inferFromString(String s)
	{
		s = s.toLowerCase();
		if(s.equals("true")) return Boolean.TRUE;
		if(s.equals("false")) return Boolean.FALSE;
		
		try{return Integer.valueOf(s);}
		catch(NumberFormatException e){}
		
		try{return Long.valueOf(s);}
		catch(NumberFormatException e){}
		
		try{return Double.valueOf(s);}
		catch(NumberFormatException e){}
		
		return s;
	}
}
