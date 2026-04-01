package a.entity.gus06.string.transform.c.gen.uuid;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210610";}


	private Service generate;

	public EntityImpl() throws Exception
	{generate = Outside.service(this,"gus06.data.generate.string.random.uuid");}
	
	public Object t(Object obj) throws Exception
	{return generate.g();}
}
