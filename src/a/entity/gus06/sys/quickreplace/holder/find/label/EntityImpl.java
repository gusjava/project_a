package a.entity.gus06.sys.quickreplace.holder.find.label;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160501";}


	private Service build;

	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.sys.quickreplace.holder.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		I holder = (I) build.t(obj);
		return holder.i();
	}
}
