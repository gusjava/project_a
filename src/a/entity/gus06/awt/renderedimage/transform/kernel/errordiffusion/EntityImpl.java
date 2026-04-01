package a.entity.gus06.awt.renderedimage.transform.kernel.errordiffusion;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.ColorCube;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}


	public Object t(Object obj) throws Exception
	{
		RenderedImage image = (RenderedImage) obj;
		
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(image);
		pb.add(ColorCube.BYTE_855);
		pb.add(KernelJAI.ERROR_FILTER_FLOYD_STEINBERG);
		
		return JAI.create("errordiffusion", pb, null);
	}
}
