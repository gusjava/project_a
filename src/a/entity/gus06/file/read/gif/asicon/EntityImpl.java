package a.entity.gus06.file.read.gif.asicon;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160208";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus.x.file.icon.read.imageio");
	}
	
	public Object t(Object obj) throws Exception
	{
		return perform.t(obj);
	}
}