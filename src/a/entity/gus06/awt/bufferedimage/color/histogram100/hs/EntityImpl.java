package a.entity.gus06.awt.bufferedimage.color.histogram100.hs;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180510";}
	
	public static final int N = 100;

	
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
			
			float h = hsb[0];
			float s = hsb[1];
			
			int index_h = (int) (h*10);
			int index_s = (int) (s*10);
			
			if(index_h == 10) index_h = 9;
			if(index_s == 10) index_s = 9;
			
			int index = index_h*10 + index_s;
			histo[index]++;
		}
		
		double[] r = new double[N];
		double total = width*height;
		for(int i=0;i<N;i++) r[i] = ((double) histo[i])/total;
		
		return r;
	}
}
