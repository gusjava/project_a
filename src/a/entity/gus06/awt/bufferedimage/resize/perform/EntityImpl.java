package a.entity.gus06.awt.bufferedimage.resize.perform;

import a.framework.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180323";}
	
	public static final String MODE_FIT = "fit";
	public static final String MODE_STRETCH = "stretch";
	public static final String MODE_WRAP = "wrap";
	public static final String MODE_FIX = "fix";


	private Service create;

	public EntityImpl() throws Exception
	{
		create = Outside.service(this,"gus06.awt.bufferedimage.create");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		BufferedImage src = (BufferedImage) o[0];
		int[] dim = (int[]) o[1];
		Color background = (Color) o[2];
		String mode = (String) o[3];
		
		BufferedImage image = (BufferedImage) create.t(dim);
		Graphics2D g = image.createGraphics();  
		g.setColor(background);  
		g.fillRect(0,0,dim[0],dim[1]);  
		
		if(mode.equals(MODE_FIT))		fit(g,dim[0],dim[1],src);
		else if(mode.equals(MODE_STRETCH))	stretch(g,dim[0],dim[1],src);
		else if(mode.equals(MODE_WRAP))		wrap(g,dim[0],dim[1],src);
		else if(mode.equals(MODE_FIX))		fix(g,dim[0],dim[1],src);
		
		else throw new Exception("Unsupported mode: "+mode);
		
		return image;
	}
	
	
	private void fit(Graphics2D g, int w, int h, BufferedImage src)
	{
		int w0 = src.getWidth();
		int h0 = src.getHeight();
	
		double cx = (double)w/(double)w0;
    		double cy = (double)h/(double)h0;

		if(cx>=cy)
		{
			int a = (int)((w-w0*cy)/2);
			int w1 = (int)(w0*cy);
			int h1 = h;
			g.drawImage(src,a,0,w1,h1,null);
		}
		else
		{
			int a = (int)((h-h0*cx)/2);	
			int w1 = w;
			int h1 = (int)(h0*cx);
			g.drawImage(src,0,a,w1,h1,null);
		}
	}
	
	
	private void stretch(Graphics2D g, int w, int h, BufferedImage src)
	{
		g.drawImage(src,0,0,w,h,null);
	}
	
	
	private void wrap(Graphics2D g, int w, int h, BufferedImage src)
	{
		int w0 = src.getWidth();
		int h0 = src.getHeight();
	
		double cx = (double)w/(double)w0;
    		double cy = (double)h/(double)h0;

		if(cx>=cy)
		{
			int a = (int)((h-h0*cx)/2);
			int w1 = w;
			int h1 = (int)(h0*cx);
			g.drawImage(src,0,a,w1,h1,null);
		}
		else
		{
			int a = (int)((w-w0*cy)/2);	
			int w1 = (int)(w0*cy);
			int h1 = h;
			g.drawImage(src,a,0,w1,h1,null);
		}
	}
	
	
	private void fix(Graphics2D g, int w, int h, BufferedImage src)
	{
		int w0 = src.getWidth();
		int h0 = src.getHeight();
		
		int x0 = (int) ((w-w0)/2);
		int y0 = (int) ((h-h0)/2);
	
		g.drawImage(src,x0,y0,w0,h0,null);
	}
}
