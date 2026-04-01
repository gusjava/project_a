package a.entity.gus06.find.long1.duration;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180118";}


	private Service stringToLong;

	public EntityImpl() throws Exception
	{
		stringToLong = Outside.service(this,"gus06.convert.stringtolong.duration");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Long) return obj;
		if(obj instanceof Integer) return Long.valueOf((Integer) obj).longValue();
		if(obj instanceof String) return stringToLong.t(obj);
		
		throw new Exception("Invalid type: "+obj.getClass().getName());
	}
}
