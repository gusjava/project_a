package a.entity.gus06.awt.bufferedimage.color.hsbdata;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180412";}

	
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
		
		float[][][] hsv_mat = new float[width][height][3];
		
		for(int i=0;i<width;i++) for(int j=0;j<height;j++)
		{
			int rgb = image.getRGB(i,j);
			Color c = new Color(rgb);
			float[] hsb = Color.RGBtoHSB(c.getRed(),c.getGreen(),c.getBlue(),null);
			
			hsv_mat[i][j][0] = hsb[0];
			hsv_mat[i][j][1] = hsb[1];
			hsv_mat[i][j][2] = hsb[2];
		}
		return hsv_mat;
	}
}
