package a.entity.gus06.awt.bufferedimage.remove.row.before;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250406";}
	
	private Service create;

	public EntityImpl() throws Exception
	{
		create = Outside.service(this,"gus06.awt.bufferedimage.create");
	}
	
	public Object t(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) obj;
		int w = image.getWidth();
		int h = image.getHeight();
		
		int newW = w;
		int newH = h-1;
		
		BufferedImage buffImg = (BufferedImage) create.t(new int[]{newW,newH});
		Graphics2D g = buffImg.createGraphics();
		
		for(int i=0;i<newW;i++) for(int j=0;j<newH;j++)
		{
			int rgb = image.getRGB(i,j+1);
			buffImg.setRGB(i,j,rgb);
		}
		g.dispose();
		return buffImg;
	}
}