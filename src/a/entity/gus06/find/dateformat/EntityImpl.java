package a.entity.gus06.find.dateformat;

import a.framework.*;
import java.text.DateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161120";}


	private Service stringToDateFormat;

	public EntityImpl() throws Exception
	{
		stringToDateFormat = Outside.service(this,"gus06.convert.stringtodateformat");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof DateFormat) return obj;
		if(obj instanceof String) return stringToDateFormat((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private DateFormat stringToDateFormat(String s) throws Exception
	{return (DateFormat) stringToDateFormat.t(s);}
}
