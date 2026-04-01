package a.entity.gus06.find.date;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141106";}


	private Service stringToDate;
	private Service intArrayToDate;

	public EntityImpl() throws Exception
	{
		stringToDate = Outside.service(this,"gus06.convert.stringtodate");
		intArrayToDate = Outside.service(this,"gus06.convert.intarraytodate");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Date) return obj;
		if(obj instanceof Long) return longToDate((Long) obj);
		if(obj instanceof int[]) return intArrayToDate((int[]) obj);
		if(obj instanceof String) return stringToDate((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Date longToDate(Long l)
	{return new Date(l.longValue());}
	
	private Date intArrayToDate(int[] t) throws Exception
	{return (Date) intArrayToDate.t(t);}
	
	private Date stringToDate(String s) throws Exception
	{return (Date) stringToDate.t(s);}
}
