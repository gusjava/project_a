package a.entity.gus06.appli.gusappmonitor.applitab.build;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190408";}

	private Service builder;

	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"factory#gus06.appli.gusappmonitor.applitab.holder");
	}
	
	public Object t(Object obj) throws Exception
	{
		P holder = (P) builder.g();
		holder.p(obj);
		return ((I) holder).i();
	}
}
