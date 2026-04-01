package a.entity.gus06.awt.renderedimage.transform.kernel.convolve;

import java.awt.image.RenderedImage;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}

	public static final int SIZE = 10;


	public Object t(Object obj) throws Exception
	{
		RenderedImage image = (RenderedImage) obj;
		
		float[] kernelMatrix = new float[SIZE*SIZE];
		for(int k=0;k<kernelMatrix.length;k++)
		kernelMatrix[k] = 1.0f/(SIZE*SIZE);
		KernelJAI kernel = new KernelJAI(SIZE,SIZE,kernelMatrix);
		
		return JAI.create("convolve",image,kernel);
	}
}
