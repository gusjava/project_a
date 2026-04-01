package a.entity.gus06.awt.renderedimage.transform.flip.diagonal;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import javax.media.jai.operator.TransposeDescriptor;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151001";}
	

	public Object t(Object obj) throws Exception
	{
		RenderedImage image = (RenderedImage) obj;
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(image);
		pb.add(TransposeDescriptor.FLIP_DIAGONAL);
		return JAI.create("transpose",pb);
	}

	static{System.setProperty("com.sun.media.jai.disableMediaLib","true");}
}
