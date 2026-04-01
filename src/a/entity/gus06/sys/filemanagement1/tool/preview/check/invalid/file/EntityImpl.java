package a.entity.gus06.sys.filemanagement1.tool.preview.check.invalid.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201010";}
	
	public static final long MIN_LENGTH = 1000;


	private Service loadImage;
	
	public EntityImpl() throws Exception
	{
		loadImage = Outside.service(this,"gus06.file.read.image.imageio");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file.length()<MIN_LENGTH)
			throw new Exception("Invalid file size: "+file.length());
		loadImage.t(file);
	}
}
