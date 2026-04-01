package a.entity.gus06.sys.jdbcmap2.perform.id.select.all;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150809";}


	private Service s;

	public EntityImpl() throws Exception
	{s = Outside.service(this,"gus06.sys.jdbcmap1.perform.id.select.all");}
	
	public Object t(Object obj) throws Exception
	{return s.t(obj);}
}
