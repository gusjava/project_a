package a.entity.gus06.file.read.svg.asimage;

import java.io.File;
import java.awt.image.BufferedImage;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250817";}

	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return null;
		
		String svgURI = file.toURI().toString();
		TranscoderInput input = new TranscoderInput(svgURI);
		BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
		transcoder.transcode(input, null);
		return transcoder.getBufferedImage();
	}
	
	
	private static class BufferedImageTranscoder extends ImageTranscoder
	{
		private BufferedImage image;
		
		public BufferedImage createImage(int width, int height)
		{return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);}
		
		public void writeImage(BufferedImage img, TranscoderOutput out)
		{this.image = img;}
		
		public BufferedImage getBufferedImage()
		{return image;}
	}
}