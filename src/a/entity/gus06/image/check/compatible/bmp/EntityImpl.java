package a.entity.gus06.image.check.compatible.bmp;

import a.framework.*;
import java.awt.image.RenderedImage;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20200110";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj instanceof RenderedImage)
		{
			RenderedImage image = (RenderedImage) obj;
			int bands = image.getData().getNumBands();
			return bands==1 || bands==3;
		}
		return true;
	}
}
