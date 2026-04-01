package a.entity.gus06.file.read.image.from.ico;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150616";}


	private Service readIco;

	public EntityImpl() throws Exception
	{
		readIco = Outside.service(this,"gus06.file.read.ico.asimage");
	}

	public Object t(Object obj) throws Exception
	{
		return readIco.t(obj);
	}
}
