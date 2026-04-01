package a.entity.gus06.convert.bufferedimagetobytearray.jpg;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201020";}

	
	public Object t(Object obj) throws Exception
	{
		BufferedImage bi = (BufferedImage) obj;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi,"jpg",baos);
		baos.flush();
		byte[] array = baos.toByteArray();
		baos.close();
	        return array;
	}
}
