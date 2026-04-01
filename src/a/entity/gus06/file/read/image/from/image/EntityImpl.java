package a.entity.gus06.file.read.image.from.image;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150616";}


	private Service readImage;


	public EntityImpl() throws Exception
	{
		readImage = Outside.service(this,"gus06.file.read.image.imageio");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return readImage.t(obj);
	}
}
