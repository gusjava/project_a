package a.entity.gus06.awt.bufferedimage.create.black;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141115";}


	private Service create;

	public EntityImpl() throws Exception
	{
		create = Outside.service(this,"gus06.awt.bufferedimage.create");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		BufferedImage img = (BufferedImage) create.t(obj);
		Graphics2D g = img.createGraphics();  
		g.setColor(Color.BLACK);  
		g.fillRect(0,0,img.getWidth(),img.getHeight());  
		return img;
	}
}
