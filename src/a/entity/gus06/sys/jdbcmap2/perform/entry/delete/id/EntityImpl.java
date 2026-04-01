package a.entity.gus06.sys.jdbcmap2.perform.entry.delete.id;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150809";}


	private Service s;

	public EntityImpl() throws Exception
	{s = Outside.service(this,"gus06.sys.jdbcmap1.perform.entry.delete.id");}
	
	public void p(Object obj) throws Exception
	{s.p(obj);}
}
