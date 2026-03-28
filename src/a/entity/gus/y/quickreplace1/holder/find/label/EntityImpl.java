package a.entity.gus.y.quickreplace1.holder.find.label;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240714";}

	private Service build;

	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus.y.quickreplace1.holder.find");
	}
	
	public Object t(Object obj) throws Exception
	{
		I holder = (I) build.t(obj);
		return holder.i();
	}
}
