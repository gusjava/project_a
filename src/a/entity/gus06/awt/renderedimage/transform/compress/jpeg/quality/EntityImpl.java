package a.entity.gus06.awt.renderedimage.transform.compress.jpeg.quality;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160529";}
	
	

	private Service findWriter;

	public EntityImpl() throws Exception
	{
		findWriter = Outside.service(this,"gus06.image.imageio.writer.jpeg");
	}
	

	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);

		RenderedImage image = (RenderedImage) t[0];
		float quality = Float.parseFloat((String) t[1]);
		
		IIOImage iooImage = new IIOImage(image, null, null);
		ImageWriter writer = (ImageWriter) findWriter.g();
		
		ImageWriteParam param = writer.getDefaultWriteParam();
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

		ByteArrayOutputStream bos = new ByteArrayOutputStream(32768);
		ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
		
		writer.setOutput(ios);
		writer.write(null, iooImage, param);
		ios.flush();
		
		ByteArrayInputStream in = new ByteArrayInputStream(bos.toByteArray());
		image = ImageIO.read(in);
		in.close();
		
		writer.dispose();
		return image;
	}
}
