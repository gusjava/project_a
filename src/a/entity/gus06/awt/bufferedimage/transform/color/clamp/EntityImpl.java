package a.entity.gus06.awt.bufferedimage.transform.color.clamp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151004";}

	
	public static final int GAP = 50;
	
	
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
		
		if(r<GAP) r=GAP;
		if(r>255-GAP) r=255-GAP;
		
		if(g<GAP) g=GAP;
		if(g>255-GAP) g=255-GAP;

		if(b<GAP) b=GAP;
		if(b>255-GAP) b=255-GAP;
		
		return new Color(r,g,b);
	}
}
