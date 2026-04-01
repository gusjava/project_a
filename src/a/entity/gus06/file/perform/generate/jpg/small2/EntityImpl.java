package a.entity.gus06.file.perform.generate.jpg.small2;

import a.framework.*;
import java.io.File;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190824";}


	private Service fileToImage;
	private Service writeImage;


	public EntityImpl() throws Exception
	{
		fileToImage = Outside.service(this,"gus06.file.read.image.small2");
		writeImage = Outside.service(this,"gus06.file.write.image.jpg");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		BufferedImage img = (BufferedImage) fileToImage.t(o[0]);
		writeImage.p(new Object[]{o[1],img});
	}
}
