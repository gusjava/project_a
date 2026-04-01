package a.entity.gus06.appli.dragontale.level1.draw.background;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200516";}



	private Service resourceLoader;
	private Service travelling;
	private BufferedImage bg;
	
	

	public EntityImpl() throws Exception
	{
		resourceLoader = Outside.service(this,"gus06.appli.dragontale.resource.loader");
		travelling = Outside.service(this,"gus06.appli.dragontale.level1.traveling");
		bg = (BufferedImage) resourceLoader.r("img-background1");
	}


	

	public void p(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) obj;
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		int offset = (int) (double) travelling.r("offset");
		int offset0 = offset%width;
		
		g.drawImage(bg,-offset0,0,width,height,null);
		if(offset0>0) g.drawImage(bg,width-offset0,0,width,height,null);
	}


}
