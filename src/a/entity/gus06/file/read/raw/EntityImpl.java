package a.entity.gus06.file.read.raw;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140929";}


	private Service perform;

	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.file.read.raw.j7");}

	public Object t(Object obj) throws Exception
	{return perform.t(obj);}
}
