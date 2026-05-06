package a.entity.gus06.file.read.string.csv2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160723";}


	private Service read;
	private Service build;


	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus.x.file.string.read.v1");
		build = Outside.service(this,"gus06.file.convert.csv2.parser");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) read.t(obj);
		return build.t(text);
	}
}
