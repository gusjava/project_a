package a.entity.gus06.awt.renderedimage.transform.color.brighten;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.awt.image.RenderedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}

	private Service toBufferedImage;
	
	public EntityImpl() throws Exception
	{
		toBufferedImage = Outside.service(this,"gus06.find.bufferedimage");
	}


	public Object t(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) toBufferedImage.t(obj);
		RescaleOp op = new RescaleOp(1.3f,0,null);
    		return op.filter(image,null);
	}
}
