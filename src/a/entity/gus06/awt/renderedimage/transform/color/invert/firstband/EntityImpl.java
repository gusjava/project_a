package a.entity.gus06.awt.renderedimage.transform.color.invert.firstband;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import a.framework.*;

public class EntityImpl implements Entity, T {



	public String creationDate() {return "20151004";}

	
	public static final double[][] matrix_invert_first_band = {
		{ -1.0d,	0.0d,		0.0d,		255.0d },
		{ 0.0d,		1.0d,		0.0d,		0.0d },
		{ 0.0d,		0.0d,		1.0d,		0.0d },
	};
	public static final double[][] matrix_invert_center_band = {
		{ 1.0d,		0.0d,		0.0d,		0.0d },
		{ 0.0d,		-1.0d,		0.0d,		255.0d },
		{ 0.0d,		0.0d,		1.0d,		0.0d },
	};
	public static final double[][] matrix_invert_last_band = {
		{ 1.0d,		0.0d,		0.0d,		0.0d },
		{ 0.0d,		1.0d,		0.0d,		0.0d },
		{ 0.0d,		0.0d,		-1.0d,		255.0d },
	};
	public static final double[][] matrix_identity = {
		{ 1.0d,		0.0d,		0.0d,		0.0d },
		{ 0.0d,		1.0d,		0.0d,		0.0d },
		{ 0.0d,		0.0d,		1.0d,		0.0d },
	};
	public static final double[][] matrix_luminance_stored_into_red_band = {
		{ .114D, 0.587D, 0.299D, 0.0D },
		{ .000D, 0.000D, 0.000D, 0.0D },
		{ .000D, 0.000D, 0.000D, 0.0D }
	};
	public static final double[][] matrix_luminance_stored_into_green_band = {
		{ .000D, 0.000D, 0.000D, 0.0D },
		{ .114D, 0.587D, 0.299D, 0.0D },
		{ .000D, 0.000D, 0.000D, 0.0D }
	};
	public static final double[][] matrix_luminance_stored_into_blue_band = {
		{ .000D, 0.000D, 0.000D, 0.0D },
		{ .000D, 0.000D, 0.000D, 0.0D },
		{ .114D, 0.587D, 0.299D, 0.0D }
	};
	public static final double[][] matrix_luminance = {
		{ .114D, 0.587D, 0.299D, 0.0D }
	};


	
	public Object t(Object obj) throws Exception
	{
		RenderedImage image = (RenderedImage) obj;
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(image);
		pb.add(matrix_invert_first_band);
		
		return JAI.create("bandcombine",pb,null);
	}
}
