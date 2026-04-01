package a.entity.gus06.file.read.image.preview.raw;

import a.framework.*;
import java.io.File;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160602";}


	private Service readImage;
	private Service imageToRawJpg;

	public EntityImpl() throws Exception
	{
		readImage = Outside.service(this,"gus06.file.read.image.preview");
		imageToRawJpg = Outside.service(this,"gus06.awt.bufferedimage.tojpg.raw");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		BufferedImage image = (BufferedImage) readImage.t(file);
		return imageToRawJpg.t(image);
	}
}
