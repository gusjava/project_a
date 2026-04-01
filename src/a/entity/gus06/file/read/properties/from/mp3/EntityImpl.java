package a.entity.gus06.file.read.properties.from.mp3;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191226";}


	private Service perform;

	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.file.mp3.extract.prop");}
	
	public Object t(Object obj) throws Exception
	{return perform.t(obj);}
}
