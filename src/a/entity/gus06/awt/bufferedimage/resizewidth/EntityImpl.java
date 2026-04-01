package a.entity.gus06.awt.bufferedimage.resizewidth;

import a.framework.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200114";}


	private Service create;

	public EntityImpl() throws Exception
	{
		create = Outside.service(this,"gus06.awt.bufferedimage.create");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		BufferedImage src = (BufferedImage) o[0];
		int w = (int) o[1];
		
		int w0 = src.getWidth();
		int h0 = src.getHeight();
		
		double cx = (double)w/(double)w0;
		int h = (int)(h0*cx);
		
		BufferedImage image = (BufferedImage) create.t(new int[]{w,h});
		Graphics2D g = image.createGraphics();  
		g.drawImage(src,0,0,w,h,null);
		
		return image;
	}
}
