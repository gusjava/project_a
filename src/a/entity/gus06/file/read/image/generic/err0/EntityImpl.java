package a.entity.gus06.file.read.image.generic.err0;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160528";}

	public static final Color COLOR = Color.LIGHT_GRAY;


	public Object g() throws Exception
	{
		BufferedImage image = new BufferedImage(2,2,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setColor(COLOR);
		g.fillRect(0,0,2,2);
		g.dispose();
		return image;
	}
}
