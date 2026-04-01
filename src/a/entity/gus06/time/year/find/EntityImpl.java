package a.entity.gus06.time.year.find;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191208";}


	private Service stringToInteger;
	private Service dateToInteger;

	public EntityImpl() throws Exception
	{
		stringToInteger = Outside.service(this,"gus06.convert.stringtointeger");
		dateToInteger = Outside.service(this,"gus06.time.date.get.year");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Integer) return obj;
		if(obj instanceof String) return stringToInteger.t(obj);
		if(obj instanceof Date) return dateToInteger.t(obj);
		
		throw new Exception("Invalid type: "+obj.getClass().getName());
	}
}
