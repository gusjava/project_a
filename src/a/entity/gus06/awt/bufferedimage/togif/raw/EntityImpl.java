package a.entity.gus06.awt.bufferedimage.togif.raw;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180408";}
	
	public static final String TYPE = "gif";
	
	
	public Object t(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) obj;
		ByteArrayOutputStream baos = null;
		byte[] raw = null;
		
		try
		{
			baos = new ByteArrayOutputStream();
			ImageIO.write(image,TYPE,baos);
			baos.flush();
			raw = baos.toByteArray();
		}
		finally
		{if(baos!=null) baos.close();}
		
		return raw;
	}
}