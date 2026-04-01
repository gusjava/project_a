package a.entity.gus06.file.read.image.imageio;

import a.framework.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140909";}


	private Service isLocked;
	
	public EntityImpl() throws Exception
	{
		isLocked = Outside.service(this,"gus06.file.filter.islocked");
	}

	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		if(isLocked.f(file)) throw new Exception("File is locked before ImageIO.read: "+file);
		BufferedImage img = ImageIO.read(file);
		if(isLocked.f(file)) throw new Exception("File is locked after ImageIO.read: "+file);
		
		return img;
	}
}
