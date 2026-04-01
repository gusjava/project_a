package a.entity.gus06.file.read.properties.from.mobi;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191010";}


	private Service perform;

	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.file.mobi.properties");}
	
	public Object t(Object obj) throws Exception
	{return perform.t(obj);}
}
