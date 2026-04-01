package a.entity.gus06.awt.renderedimage.transform.scale.lessthan150000;

import java.awt.image.RenderedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191129";}

	public static final String LIMIT = "150000";


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.renderedimage.transform.scale.lessthan");
	}

	public Object t(Object obj) throws Exception
	{
		RenderedImage image = (RenderedImage) obj;
		return perform.t(new Object[]{image,LIMIT});
	}
}
