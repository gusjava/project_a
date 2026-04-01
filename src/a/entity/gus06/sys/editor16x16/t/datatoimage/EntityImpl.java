package a.entity.gus06.sys.editor16x16.t.datatoimage;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250828";}
	
	public final static int NB = 16;
	public final static String TRANSPARENT = "255-255-255-0";
	
	
	private Service decodeColor;
	
	public EntityImpl() throws Exception
	{
		decodeColor = Outside.service(this,"gus06.sys.editor16x16.c.decode1");
	}

	
	public Object t(Object obj) throws Exception
	{
		String[][] data = (String[][]) obj;
		int width = data.length;
		int height = data[0].length;
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for(int i=0;i<width;i++)
		for(int j=0;j<height;j++)
		{
			Color c = (Color) decodeColor.t(data[i][j]);
			image.setRGB(j, i, c.getRGB());
		}
		return image;
	}
}