package a.entity.gus06.file.write.image.jpg;

import a.framework.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141015";}


	private Service toOpaqueRGB;
	
	public EntityImpl() throws Exception
	{
		toOpaqueRGB = Outside.service(this,"gus06.awt.bufferedimage.transform.opaquergb");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		BufferedImage image = (BufferedImage) toOpaqueRGB.t(o[1]);
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		ImageIO.write(image,"jpg",file);
	}
}
