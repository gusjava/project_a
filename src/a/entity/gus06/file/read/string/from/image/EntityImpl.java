package a.entity.gus06.file.read.string.from.image;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210607";}


	private Service fileFromImage;


	public EntityImpl() throws Exception
	{
		fileFromImage = Outside.service(this,"gus06.sys.tesseract1.engine");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return fileFromImage.t(obj);
	}
}