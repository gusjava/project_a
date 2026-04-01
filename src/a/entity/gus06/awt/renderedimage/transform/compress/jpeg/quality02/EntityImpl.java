package a.entity.gus06.awt.renderedimage.transform.compress.jpeg.quality02;

import java.awt.image.RenderedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151004";}

	public final static String QUALITY = "0.2";


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