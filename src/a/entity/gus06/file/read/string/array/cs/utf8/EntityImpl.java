package a.entity.gus06.file.read.string.array.cs.utf8;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190413";}


	private Service read;


	public EntityImpl() throws Exception
	{read = Outside.service(this,"gus06.file.read.string.cs.utf8");}
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) read.t(obj);
		return text.replace("\r","").split("\n");
	}
}
