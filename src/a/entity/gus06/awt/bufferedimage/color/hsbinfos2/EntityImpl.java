package a.entity.gus06.awt.bufferedimage.color.hsbinfos2;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180412";}

	
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
		
		double h_sum = 0;
		double s_sum = 0;
		double b_sum = 0;
		
		double h_min = 1000;
		double s_min = 1000;
		double b_min = 1000;
		
		double h_max = 0;
		double s_max = 0;
		double b_max = 0;
		
		for(int i=0;i<x;i++) for(int j=0;j<y;j++)
		{
			float h = hsv_mat[i][j][0];
			float s = hsv_mat[i][j][1];
			float b = hsv_mat[i][j][2];
			
			h_sum += h;
			s_sum += s;
			b_sum += b;
			
			if(h>h_max) h_max = h;
			if(s>s_max) s_max = s;
			if(b>b_max) b_max = b;
			
			if(h<h_min) h_min = h;
			if(s<s_min) s_min = s;
			if(b<b_min) b_min = b;
		}
		
		double h_av = h_sum/nb;
		double s_av = s_sum/nb;
		double b_av = b_sum/nb;
		
		h_sum = 0;
		s_sum = 0;
		b_sum = 0;
		
		for(int i=0;i<x;i++) for(int j=0;j<y;j++)
		{
			float h = hsv_mat[i][j][0];
			float s = hsv_mat[i][j][1];
			float b = hsv_mat[i][j][2];
			
			h_sum += Math.pow(h-h_av,2);
			s_sum += Math.pow(s-s_av,2);
			b_sum += Math.pow(b-b_av,2);
		}
		
		double h_et = Math.sqrt(h_sum/nb);
		double s_et = Math.sqrt(s_sum/nb);
		double b_et = Math.sqrt(b_sum/nb);
		
		return new double[]{
			h_av,s_av,b_av,
			h_et,s_et,b_et,
			h_min,s_min,b_min,
			h_max,s_max,b_max};
	}
}
