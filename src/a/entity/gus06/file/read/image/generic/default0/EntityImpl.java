package a.entity.gus06.file.read.image.generic.default0;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150616";}

	public static final Color COLOR = Color.WHITE;


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null || !file.isFile()) throw new Exception("Invalid file: "+file);
		
		BufferedImage image = new BufferedImage(2,2,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setColor(COLOR);
		g.fillRect(0,0,2,2);
		g.dispose();
		
		return image;
	}
}
