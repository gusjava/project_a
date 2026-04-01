package a.entity.gus06.data.perform.height;

import a.framework.*;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.image.RenderedImage;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}



	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Image)
		{
			Image im = (Image) obj;
			return Integer.valueOf(im.getHeight(null));
		}
		if(obj instanceof RenderedImage)
		{
			RenderedImage im = (RenderedImage) obj;
			return Integer.valueOf(im.getHeight());
		}
		if(obj instanceof Rectangle)
		{
			Rectangle rect = (Rectangle) obj;
			return Integer.valueOf(rect.height);
		}
		if(obj instanceof Dimension)
		{
			Dimension dim = (Dimension) obj;
			return Integer.valueOf(dim.height);
		}
		if(obj instanceof Component)
		{
			Component comp = (Component) obj;
			return Integer.valueOf(comp.getHeight());
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
