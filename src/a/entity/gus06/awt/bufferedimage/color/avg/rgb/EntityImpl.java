package a.entity.gus06.awt.bufferedimage.color.avg.rgb;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141122";}

	
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
		
		double r = 0;
		double g = 0;
		double b = 0;
		
		for(int i=0;i<width;i++) for(int j=0;j<height;j++)
		{
			int rgb = image.getRGB(i,j);
			Color c = new Color(rgb);
			
			r += c.getRed();
			g += c.getGreen();
			b += c.getBlue();
		}
		
		double nb = width*height;
		int r_av = (int) (r/nb);
		int g_av = (int) (g/nb);
		int b_av = (int) (b/nb);
		
		return new Color(r_av,g_av,b_av);
	}
}
