package a.entity.gus06.file.opendocument.ods.filetoimage;

import a.framework.*;
import java.io.File;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import org.jopendocument.model.OpenDocument;
import org.jopendocument.renderer.ODTRenderer;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210609";}

	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		OpenDocument doc = new OpenDocument(file);
		ODTRenderer renderer = new ODTRenderer(doc);
		
		int w = renderer.getWidth();
		int h = renderer.getHeight();
		
		BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		
		renderer.paintComponent(graphics);
		graphics.dispose();
		return image;
	}
}