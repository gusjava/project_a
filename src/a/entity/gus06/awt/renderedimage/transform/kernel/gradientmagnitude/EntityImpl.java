package a.entity.gus06.awt.renderedimage.transform.kernel.gradientmagnitude;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}



	public Object t(Object obj) throws Exception
	{
		RenderedImage image = (RenderedImage) obj;

		float data_h[] = new float[] {
				1.0F, 0.0F, -1.0F,
				1.414F, 0.0F, -1.414F,
				1.0F, 0.0F, -1.0F};
		
		float data_v[] = new float[] {
				-1.0F, -1.414F, -1.0F,
				0.0F, 0.0F, 0.0F,
				1.0F, 1.414F, 1.0F};

		KernelJAI kern_h = new KernelJAI(3,3,data_h);
		KernelJAI kern_v = new KernelJAI(3,3,data_v);

		image = JAI.create("gradientmagnitude",image,kern_h, kern_v);
		
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(image);
		return JAI.create("invert",pb);
	}
}
