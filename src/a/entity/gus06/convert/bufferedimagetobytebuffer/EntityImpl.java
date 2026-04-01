package a.entity.gus06.convert.bufferedimagetobytebuffer;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.nio.ByteBuffer;
import java.awt.image.DataBuffer;
import java.nio.ByteOrder;
import java.awt.Graphics2D;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160719";}

	
	
	public Object t(Object obj) throws Exception
	{
		BufferedImage bi = (BufferedImage) obj;
		DataBuffer buff = bi.getRaster().getDataBuffer();
		
		if (!(buff instanceof DataBufferByte))
		{
			bi = convertImageToGrayscale(bi);
			buff = bi.getRaster().getDataBuffer();
	        }
	
	        byte[] pixelData = ((DataBufferByte) buff).getData();
	
	        ByteBuffer buf = ByteBuffer.allocateDirect(pixelData.length);
	        buf.order(ByteOrder.nativeOrder());
	        buf.put(pixelData);
	        buf.flip();
	
	        return buf;
	}
	
	
	private BufferedImage convertImageToGrayscale(BufferedImage image)
	{
		BufferedImage tmp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	        Graphics2D g2 = tmp.createGraphics();
	        g2.drawImage(image, 0, 0, null);
	        g2.dispose();
	        return tmp;
	}
}
