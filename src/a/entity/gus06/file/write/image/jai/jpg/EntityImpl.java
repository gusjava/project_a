package a.entity.gus06.file.write.image.jai.jpg;

import java.io.File;
import java.io.FileOutputStream;
import java.awt.image.RenderedImage;
import javax.media.jai.JAI;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150929";}
	
	public static final String TYPE = "JPEG";


	private Service findRenderedImage;

	public EntityImpl() throws Exception
	{
		findRenderedImage = Outside.service(this,"gus06.find.renderedimage");
	}



	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);

		File file = (File) t[0];
		RenderedImage image = (RenderedImage) findRenderedImage.t(t[1]);
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		FileOutputStream stream = new FileOutputStream(file);
		JAI.create("encode", image, stream, TYPE, null);
		JAI.create("filestore", image, file.getAbsolutePath(), TYPE, null);
		
		stream.close();
		/*
		 * Le fichier file reste v�rouill� par l'application Java ......
		 */
	}
}
