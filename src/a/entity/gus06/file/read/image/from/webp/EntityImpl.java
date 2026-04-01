package a.entity.gus06.file.read.image.from.webp;

import a.framework.*;
import java.io.File;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import com.luciad.imageio.webp.WebPImageReaderSpi;
import com.luciad.imageio.webp.WebPReader;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250131";}
	
	private WebPImageReaderSpi spi = new WebPImageReaderSpi();

	public EntityImpl() throws Exception
	{
	}

	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return readFromFile((File) obj);
		if(obj instanceof InputStream) return readFromInputStream((InputStream) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object readFromFile(File f) throws Exception
	{
		try (ImageInputStream input = ImageIO.createImageInputStream(f))
		{
			if (input == null) throw new Exception("Could not create ImageInputStream for " + f);
			ImageReader reader = new WebPReader(spi);
			reader.setInput(input);
			return reader.read(0);
		}
	}
	
	private Object readFromInputStream(InputStream is) throws Exception
	{
		try (ImageInputStream input = ImageIO.createImageInputStream(is))
		{
			if (input == null) throw new Exception("Could not create ImageInputStream from inputStream");
			ImageReader reader = new WebPReader(spi);
			reader.setInput(input);
			return reader.read(0);
		}
	}
}