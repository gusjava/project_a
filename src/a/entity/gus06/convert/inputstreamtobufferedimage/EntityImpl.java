package a.entity.gus06.convert.inputstreamtobufferedimage;

import a.framework.*;
import javax.imageio.ImageIO;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190921";}
	
	public Object t(Object obj) throws Exception
	{
		try(InputStream is = (InputStream) obj)
		{return ImageIO.read(is);}
	}
}