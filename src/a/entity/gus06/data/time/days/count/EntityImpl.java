package a.entity.gus06.data.time.days.count;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191208";}


	private Service handleYear;
	private Service handleYyyymm;

	public EntityImpl() throws Exception
	{
		handleYear = Outside.service(this,"gus06.data.time.days.count.year");
		handleYyyymm = Outside.service(this,"gus06.data.time.days.count.yyyymm");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Integer) return handleYear.t(obj);
		if(obj instanceof int[]) return handleYyyymm.t(obj);
		if(obj instanceof String)
		{
			String s = (String) obj;
			if(s.length()<=4) return handleYear.t(obj);
			return handleYyyymm.t(obj);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
