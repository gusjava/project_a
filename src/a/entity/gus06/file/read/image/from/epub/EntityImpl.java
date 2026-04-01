package a.entity.gus06.file.read.image.from.epub;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191010";}


	private Service perform;

	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.file.epub.cover.asimage");}
	
	public Object t(Object obj) throws Exception
	{return perform.t(obj);}
}
