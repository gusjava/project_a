package a.entity.gus06.file.info.duration.asstring;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250512";}


	private Service duration;
	
	
	public EntityImpl() throws Exception
	{
		duration = Outside.service(this,"gus06.file.info.duration");
	}



	public Object t(Object obj) throws Exception
	{
		Long v = (Long) duration.t(obj);
		return v!=null?""+v:"";
	}
}