package a.entity.gus06.sys.filetool.ext.entityimporter1.settings;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250628";}
	
	
	
	private Service factory;
	
	public EntityImpl() throws Exception
	{factory = Outside.service(this,"factory#gus.sys.filetool.ext.entityimporter1.settings.holder");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object holder = factory.g();
		((P)holder).p(obj);
		return ((I)holder).i();
	}
}