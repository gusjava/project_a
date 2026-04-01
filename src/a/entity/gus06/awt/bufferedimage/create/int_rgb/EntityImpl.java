package a.entity.gus06.awt.bufferedimage.create.int_rgb;

import java.awt.image.BufferedImage;
import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150930";}


	private Service toDim;
	
	public EntityImpl() throws Exception
	{toDim = Outside.service(this,"gus06.find.dimension");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Dimension dim = (Dimension) toDim.t(obj);
		return new BufferedImage(dim.width,dim.height,BufferedImage.TYPE_INT_RGB);
	}
}
