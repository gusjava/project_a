package a.entity.gus06.awt.renderedimage.transform.compress.jpeg.quality06;

import java.awt.image.RenderedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160529";}

	public final static String QUALITY = "0.6";


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.renderedimage.transform.compress.jpeg.quality");
	}
	
	public Object t(Object obj) throws Exception
	{
		RenderedImage image = (RenderedImage) obj;
		return perform.t(new Object[]{image,QUALITY});
	}
}