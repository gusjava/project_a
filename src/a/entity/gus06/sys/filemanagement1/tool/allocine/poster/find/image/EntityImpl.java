package a.entity.gus06.sys.filemanagement1.tool.allocine.poster.find.image;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201004";}
	
	
	private Service loadImage;
	private Service findFile;

	public EntityImpl() throws Exception
	{
		loadImage = Outside.service(this,"gus06.file.read.image.imageio");
		findFile = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.poster.find.file");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) findFile.t(obj);
		if(file==null || !file.exists()) return null;
		return loadImage.t(file);
	}
}
