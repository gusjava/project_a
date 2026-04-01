package a.entity.gus06.file.write.image.small3.jpg;

import java.awt.image.RenderedImage;
import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190824";}


	
	private Service scale;
	private Service writeJpeg09;


	public EntityImpl() throws Exception
	{
		scale = Outside.service(this,"gus06.awt.renderedimage.transform.scale.lessthan1000000");
		writeJpeg09 = Outside.service(this,"gus06.file.write.image.imageio.jpg.quality09");
	}


	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);

		File file = (File) t[0];
		RenderedImage image = (RenderedImage) t[1];
		
		image = (RenderedImage) scale.t(image);
		writeJpeg09.p(new Object[]{file,image});
	}
}
