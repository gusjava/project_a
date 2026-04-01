package a.entity.gus06.filter.string.is.yearp;

import a.framework.*;
import java.util.Calendar;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160612";}

	private int thisYear;

	public EntityImpl() throws Exception
	{
		Calendar now = Calendar.getInstance(); 
		thisYear = now.get(Calendar.YEAR);
	}
	
	public boolean f(Object obj) throws Exception
	{
		int n = int_((String) obj);
		return n>=1900 && n<=thisYear;
	}
	
	private int int_(String s)
	{
		try{return Integer.parseInt(s);}
		catch(NumberFormatException e){return -1;}
	}
}
