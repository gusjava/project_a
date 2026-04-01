package a.entity.gus06.awt.bufferedimage.transform.color.invert;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}

	
	public static final int RGB_BLACK = -16777216;
	public static final int RGB_WHITE = -1;
	
	
	private Service toBufferedImage;
	private Service invColor;
	
	public EntityImpl() throws Exception
	{
		toBufferedImage = Outside.service(this,"gus06.find.bufferedimage");
		invColor = Outside.service(this,"gus06.awt.color.inv.rgb");
	}


	public Object t(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) toBufferedImage.t(obj);
		int width = image.getWidth();
		int heigth = image.getHeight();
		
		BufferedImage buffImg = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		
		
		for(int i=0;i<width;i++) for(int j=0;j<heigth;j++)
		{
			int rgb = image.getRGB(i,j);
			Color c = new Color(rgb);
			c = (Color) invColor.t(c);
			
			g.setColor(c);
			g.drawRect(i,j,1,1);
		}
		g.dispose();
		return buffImg;
	}
}
