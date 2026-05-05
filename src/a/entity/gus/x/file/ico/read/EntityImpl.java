package a.entity.gus.x.file.ico.read;

import java.io.File;
import net.sf.image4j.codec.ico.ICODecoder;
import a.framework.*;
import java.util.List;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20191217";}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return null;
		List images = ICODecoder.read(file);
		if(images.isEmpty()) return null;
		return images;
	}
}
