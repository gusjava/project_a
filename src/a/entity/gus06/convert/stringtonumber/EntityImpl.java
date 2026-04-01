package a.entity.gus06.convert.stringtonumber;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151205";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return toNumber((String) obj);
	}
	
	
	
	private Number toNumber(String s) throws Exception
	{
		if(isInt(s)) return Integer.valueOf(s);
		if(isLong(s)) return Long.valueOf(s);
		if(isDouble(s)) return Double.valueOf(s);
		
		throw new Exception("Could not convert string into number: "+s);
	}
	
	private boolean isInt(String s)
	{
		try{Integer.parseInt(s);return true;}
		catch(NumberFormatException e) {return false;}
	}
	
	private boolean isLong(String s)
	{
		try{Long.parseLong(s);return true;}
		catch(NumberFormatException e) {return false;}
	}
	
	private boolean isDouble(String s)
	{
		try{Double.parseDouble(s);return true;}
		catch(NumberFormatException e) {return false;}
	}
}
