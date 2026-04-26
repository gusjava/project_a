package a.entity.gus06.file.read.icon.from.gif;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160209";}


	private Service t;


	public EntityImpl() throws Exception
	{
		t = Outside.service(this,"gus.x.file.icon.read.imageio");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return t.t(obj);
	}
}