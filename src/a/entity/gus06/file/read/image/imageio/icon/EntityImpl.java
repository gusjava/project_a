package a.entity.gus06.file.read.image.imageio.icon;

import java.io.File;
import javax.swing.ImageIcon;
import a.framework.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201206";}

	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null || !file.exists()) return null;
		
		BufferedImage img = ImageIO.read(file);
		return new ImageIcon(img);
	}
}