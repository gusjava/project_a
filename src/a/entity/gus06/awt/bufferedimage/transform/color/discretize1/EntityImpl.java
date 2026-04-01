package a.entity.gus06.awt.bufferedimage.transform.color.discretize1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {



	public String creationDate() {return "20151004";}

	
	
	private Service toBufferedImage;
	
	
	public EntityImpl() throws Exception
	{
		toBufferedImage = Outside.service(this,"gus06.find.bufferedimage");
	}


	public Object t(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) toBufferedImage.t(obj);
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage buffImg = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		
		for(int i=0;i<width;i++) for(int j=0;j<height;j++)
		{
			int rgb = image.getRGB(i,j);
			Color c = new Color(rgb);
			g.setColor(buildColor(c));
			g.drawRect(i,j,1,1);
		}
		g.dispose();
		return buffImg;
	}
	
	
	
	private Color buildColor(Color c)
	{
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		
		r = r>127?255:0;
		g = g>127?255:0;
		b = b>127?255:0;
		
		return new Color(r,g,b);
	}
}
