package a.entity.gus06.awt.renderedimage.transform.scale.lessthan;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160529";}
	


	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);

		RenderedImage image = (RenderedImage) t[0];
		float limit = Float.parseFloat((String) t[1]);
		
		
		long size = image.getHeight()*image.getWidth();
		if(size<=limit) return image;
		
		float factor = (float) Math.sqrt((double)limit/(double)size);
		return resize(image,factor);
	}
	
	
	
	private RenderedImage resize(RenderedImage image, float factor)
	{
		return image;
		
		//TODO : JAI.create ne fonctionne plus depuis Java9 ... 
		//il faudra trouver une autre solution 
		
		//Caused by: java.lang.IllegalAccessError: 
		//class javax.media.jai.RasterAccessor (in unnamed module @0x8434096) 
		//cannot access class sun.awt.image.BytePackedRaster (in module java.desktop) 
		//because module java.desktop does not export sun.awt.image to unnamed module @0x8434096

//		ParameterBlock pb = new ParameterBlock();
//		pb.addSource(image);
//		pb.add(factor); // The xScale
//		pb.add(factor); // The yScale
//		pb.add(0.0f); // The x translation
//		pb.add(0.0f); // The y translation
//		pb.add(new InterpolationNearest());
//		return JAI.create("scale",pb);
	}


	
	static{System.setProperty("com.sun.media.jai.disableMediaLib","true");}
}