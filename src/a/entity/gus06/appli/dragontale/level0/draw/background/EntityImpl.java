package a.entity.gus06.appli.dragontale.level0.draw.background;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import a.framework.*;



public class EntityImpl implements Entity, P {


	public String creationDate() {return "20200516";}



	private Service resourceLoader;
	private BufferedImage bg;
	
	private double x = 0;
	private double dx = 0.2;
	
	

	public EntityImpl() throws Exception
	{
		resourceLoader = Outside.service(this,"gus06.appli.dragontale.resource.loader");
		bg = (BufferedImage) resourceLoader.r("img-background0");
	}

	

	public void p(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) obj;
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		x += dx;
		if(x >= width) x = 0;
		
		if(x>0) g.drawImage(bg,(int)x-width,0,width,height,null);
		g.drawImage(bg,(int)x,0,width,height,null);
	}
}
