package a.entity.gus06.awt.bufferedimage.color.histogram10.hue;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180505";}
	
	public static final int N = 10;

	
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
		
		long[] histo = new long[N];
		for(int i=0;i<N;i++) histo[i] = 0;
		
		for(int i=0;i<width;i++) for(int j=0;j<height;j++)
		{
			int rgb = image.getRGB(i,j);
			Color c = new Color(rgb);
			float[] hsb = Color.RGBtoHSB(c.getRed(),c.getGreen(),c.getBlue(),null);
			
			float hue = hsb[0];
			int index = (int) (hue*10);
			if(index == 10) index = 9;
			histo[index]++;
		}
		
		double[] r = new double[N];
		double total = width*height;
		for(int i=0;i<N;i++) r[i] = ((double) histo[i])/total;
		
		return r;
	}
}
