package a.entity.gus06.file.read.image.from.svg;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250817";}


	private Service readSVG;

	public EntityImpl() throws Exception
	{
		readSVG = Outside.service(this,"gus06.file.read.svg.asimage");
	}

	public Object t(Object obj) throws Exception
	{
		return readSVG.t(obj);
	}
}