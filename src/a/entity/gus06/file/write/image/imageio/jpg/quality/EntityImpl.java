package a.entity.gus06.file.write.image.imageio.jpg.quality;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160526";}



	private Service findRenderedImage;
	private Service findWriter;

	public EntityImpl() throws Exception
	{
		findRenderedImage = Outside.service(this,"gus06.find.renderedimage");
		findWriter = Outside.service(this,"gus06.image.imageio.writer.jpeg");
	}
	

	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=3) throw new Exception("Wrong data number: "+t.length);

		File file = (File) t[0];
		RenderedImage image = (RenderedImage) findRenderedImage.t(t[1]);
		float quality = Float.parseFloat((String) t[2]);
		
		IIOImage iooImage = new IIOImage(image, null, null);
		ImageWriter writer = (ImageWriter) findWriter.g();
		
		ImageWriteParam param = writer.getDefaultWriteParam();
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(quality);
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		FileOutputStream fos = new FileOutputStream(file);
		ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
		
		writer.setOutput(ios);
		writer.write(null,iooImage,param);
		ios.flush();
		
		fos.close();
		
		writer.dispose();
	}
}
