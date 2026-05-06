package a.entity.gus06.file.read.string.array;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140926";}


	private Service read;


	public EntityImpl() throws Exception
	{read = Outside.service(this,"gus.x.file.string.read.v1");}
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) read.t(obj);
		return text.replace("\r","").split("\n");
	}
}
