package a.entity.gus06.awt.bufferedimage.color.hsbinfos3;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180531";}

	
	private Service toBufferedImage;
	private Service buildData;
	
	public EntityImpl() throws Exception
	{
		toBufferedImage = Outside.service(this,"gus06.find.bufferedimage");
		buildData = Outside.service(this,"gus06.awt.bufferedimage.color.hsbdata");
	}


	public Object t(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) toBufferedImage.t(obj);
		float[][][] hsv_mat = (float[][][]) buildData.t(image);
		
		int x = image.getWidth();
		int y = image.getHeight();
		double nb = x*y;
		
		double h1_sum = 0;
		int h1_nb = 0;
		
		double h2_sum = 0;
		int h2_nb = 0;
		
		double s_sum = 0;
		double b_sum = 0;
		
		for(int i=0;i<x;i++) for(int j=0;j<y;j++)
		{
			float h = hsv_mat[i][j][0];
			float s = hsv_mat[i][j][1];
			float b = hsv_mat[i][j][2];
			
			if(h<0.5f)
			{
				h1_sum += h;
				h1_nb++;
			}
			else
			{
				h2_sum += h;
				h2_nb++;
			}
			
			s_sum += s;
			b_sum += b;
		}
		
		double h1_av = h1_sum/h1_nb;
		double h2_av = h2_sum/h2_nb;
		
		double s_av = s_sum/nb;
		double b_av = b_sum/nb;
		
		h1_sum = 0;
		h2_sum = 0;
		s_sum = 0;
		b_sum = 0;
		
		for(int i=0;i<x;i++) for(int j=0;j<y;j++)
		{
			float h = hsv_mat[i][j][0];
			float s = hsv_mat[i][j][1];
			float b = hsv_mat[i][j][2];
			
			if(h<0.5f)
			{
				h1_sum += Math.pow(h-h1_av,2);
			}
			else
			{
				h2_sum += Math.pow(h-h2_av,2);
			}
			
			s_sum += Math.pow(s-s_av,2);
			b_sum += Math.pow(b-b_av,2);
		}
		
		double h1_et = Math.sqrt(h1_sum/h1_nb);
		double h2_et = Math.sqrt(h2_sum/h2_nb);
		
		double s_et = Math.sqrt(s_sum/nb);
		double b_et = Math.sqrt(b_sum/nb);
		
		return new double[]{
			h1_av,h2_av,s_av,b_av,
			h1_et,h2_et,s_et,b_et,};
	}
}
