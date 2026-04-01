package a.entity.gus06.file.read.png.asicon;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160209";}

	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.read.image.imageio.icon");
	}
	
	public Object t(Object obj) throws Exception
	{
		return perform.t(obj);
	}
}