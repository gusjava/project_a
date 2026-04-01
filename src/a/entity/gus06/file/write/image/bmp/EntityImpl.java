package a.entity.gus06.file.write.image.bmp;

import a.framework.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180311";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		BufferedImage image = (BufferedImage) o[1];
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		ImageIO.write(image,"bmp",file);
	}
}
