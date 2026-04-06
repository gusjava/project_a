package a.entity.gus06.sys.filetool.ext.notes1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210514";}
	
	
	
	private Service factory;
	
	public EntityImpl() throws Exception
	{factory = Outside.service(this,"factory#gus06.sys.filetool.ext.notes1.holder");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object holder = factory.g();
		((P)holder).p(obj);
		return ((I)holder).i();
	}
}