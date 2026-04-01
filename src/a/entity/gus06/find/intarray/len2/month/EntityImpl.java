package a.entity.gus06.find.intarray.len2.month;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191208";}


	private Service stringToIntArray;
	private Service dateToIntArray;

	public EntityImpl() throws Exception
	{
		stringToIntArray = Outside.service(this,"gus06.convert.stringtointarray.yearmonth");
		dateToIntArray = Outside.service(this,"gus06.time.date.get.yearmonth");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof int[])
		{
			int[] d = (int[]) obj;
			if(d.length!=2) throw new Exception("Invalid array size: "+d.length);
			return d;
		}
		if(obj instanceof String)
		{
			return stringToIntArray.t(obj);
		}
		if(obj instanceof Date)
		{
			return dateToIntArray.t(obj);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
