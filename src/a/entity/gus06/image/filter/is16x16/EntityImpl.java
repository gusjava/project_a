package a.entity.gus06.image.filter.is16x16;

import a.framework.*;
import java.awt.image.RenderedImage;
import java.awt.Image;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20250228";}
	
	public boolean f(Object obj) throws Exception
	{
		if(obj instanceof RenderedImage) return isIcon16x16((RenderedImage) obj);
		if(obj instanceof Image) return isIcon16x16((Image) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private boolean isIcon16x16(RenderedImage img)
	{
		int w = img.getWidth();
		int h = img.getHeight();
		return w==16 && h==16;
	}
	
	private boolean isIcon16x16(Image img)
	{
		int w = img.getWidth(null);
		int h = img.getHeight(null);
		return w==16 && h==16;
	}
}