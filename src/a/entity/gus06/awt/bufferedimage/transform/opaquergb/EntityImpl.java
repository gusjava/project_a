package a.entity.gus06.awt.bufferedimage.transform.opaquergb;

import a.framework.*;
import java.awt.image.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180308";}
	
	private static final int[] RGB_MASKS = {0xFF0000, 0xFF00, 0xFF};
	private static final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);

	private static final long MAX_PIXELS = 30_000_000L;

	private Service toImage;

	public EntityImpl() throws Exception
	{
		toImage = Outside.service(this,"gus06.find.bufferedimage");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) toImage.t(obj);
		int w = image.getWidth();
		int h = image.getHeight();
		
		long pixelCount = (long) w * (long) h;
		if(pixelCount > MAX_PIXELS) throw new Exception("Image trop grande : " + w + "x" + h + " = " + pixelCount + " pixels");
		
		int[] pixelBuffer = image.getRGB(0, 0, w, h, null, 0, w);
		DataBuffer buffer = new DataBufferInt(pixelBuffer, w * h);
		WritableRaster raster = Raster.createPackedRaster(buffer, w, h, w, RGB_MASKS, null);
		return new BufferedImage(RGB_OPAQUE, raster, false, null);
	}
	
	
//	public Object t(Object obj) throws Exception
//	{
//		BufferedImage image = (BufferedImage) toImage.t(obj);
//		PixelGrabber pg = new PixelGrabber(image, 0, 0, -1, -1, true);
//		pg.grabPixels();
//		
//		int w = pg.getWidth();
//		int h = pg.getHeight();
//		int[] pixelBuffer = (int[]) pg.getPixels();
//		
//		DataBuffer buffer = new DataBufferInt(pixelBuffer, w * h);
//		WritableRaster raster = Raster.createPackedRaster(buffer, w, h, w, RGB_MASKS, null);
//		return new BufferedImage(RGB_OPAQUE, raster, false, null);
//	}
}