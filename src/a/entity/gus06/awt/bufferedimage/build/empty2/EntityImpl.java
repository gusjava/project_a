package a.entity.gus06.awt.bufferedimage.build.empty2;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200515";}


	private Service build;
	
	public EntityImpl() throws Exception
	{build = Outside.service(this,"gus06.awt.bufferedimage.build.empty1");}
	
	
	

	public Object t(Object obj) throws Exception
	{
		BufferedImage img = (BufferedImage) build.t(obj);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		return img;
	}
}
