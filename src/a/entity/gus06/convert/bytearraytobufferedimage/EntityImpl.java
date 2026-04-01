package a.entity.gus06.convert.bytearraytobufferedimage;

import a.framework.*;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150930";}


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		byte[] bytes = (byte[]) obj;
		InputStream in = new ByteArrayInputStream(bytes);
		BufferedImage bi = ImageIO.read(in);
		in.close();
		
		if(bi==null) throw new Exception("Failed to load image from byte array (length="+bytes.length+")");
		return bi;
	}
}
