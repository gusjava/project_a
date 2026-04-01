package a.entity.gus06.awt.bufferedimage.merge.grid;

import a.framework.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180331";}
	

	public Object t(Object obj) throws Exception
	{
		Image[][] im = (Image[][]) obj;
		
		int x = im.length;
		if(x==0) throw new Exception("Invalid matrix width: 0");
		int y = im[0].length;
		if(y==0) throw new Exception("Invalid matrix height: 0");
		
		int w = 0;
		int h = 0;
		
		for(int i=0;i<x;i++)
		for(int j=0;j<y;j++)
		if(im[i][j]!=null)
		{
			int w0 = im[i][j].getWidth(null);
			int h0 = im[i][j].getHeight(null);
			
			if(w0>w) w = w0;
			if(h0>h) h = h0;
		}
		
		if(w==0) throw new Exception("Invalid image width: 0");
		if(h==0) throw new Exception("Invalid image height: 0");
		
		BufferedImage image = new BufferedImage(w*y, h*x, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		
		for(int i=0;i<x;i++)
		for(int j=0;j<y;j++)
		if(im[i][j]!=null)
		{
			g.drawImage(im[i][j],j*w,i*h,w,h,null);
		}
		
		g.dispose();
		return image;
	}
}
