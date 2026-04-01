package a.entity.gus06.file.info.string.endofline.asstring;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221108";}
	
	private Service check;
	private Service info;
	
	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.file.string.check");
		info = Outside.service(this,"gus06.file.string.info.endofline");
	}


	public Object t(Object obj) throws Exception
	{
		if(!check.f(obj)) return "";
		return info.t(obj);
	}
}