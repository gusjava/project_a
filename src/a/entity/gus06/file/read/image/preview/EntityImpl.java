package a.entity.gus06.file.read.image.preview;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160526";}


	private Service readImage;
	private Service scale;
	private Service toBufferedImage;


	public EntityImpl() throws Exception
	{
		readImage = Outside.service(this,"gus06.file.read.image.generic");
		scale = Outside.service(this,"gus06.awt.renderedimage.transform.scale.lessthan200000");
		toBufferedImage = Outside.service(this,"gus06.find.bufferedimage");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Object image = readImage.t(file);
		Object preview = scale.t(image);
		return toBufferedImage.t(preview);
	}
}