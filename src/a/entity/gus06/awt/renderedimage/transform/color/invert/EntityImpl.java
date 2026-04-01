package a.entity.gus06.awt.renderedimage.transform.color.invert;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151004";}



	public Object t(Object obj) throws Exception
	{
		RenderedImage image = (RenderedImage) obj;
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(image);
		return JAI.create("invert",pb);
	}
}
