package a.entity.gus06.file.read.string.array.autodetect;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151018";}


	private Service read;
	private Service split;


	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.string.autodetect");
		split = Outside.service(this,"gus06.string.split.lines1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) read.t(obj);
		return split.t(text);
	}
}
