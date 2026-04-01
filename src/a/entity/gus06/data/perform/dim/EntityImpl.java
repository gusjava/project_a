package a.entity.gus06.data.perform.dim;

import a.framework.*;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.image.RenderedImage;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180325";}



	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Image)
		{
			Image im = (Image) obj;
			return new int[]{im.getWidth(null),im.getHeight(null)};
		}
		if(obj instanceof RenderedImage)
		{
			RenderedImage im = (RenderedImage) obj;
			return new int[]{im.getWidth(),im.getHeight()};
		}
		if(obj instanceof Rectangle)
		{
			Rectangle rect = (Rectangle) obj;
			return new int[]{rect.width,rect.height};
		}
		if(obj instanceof Dimension)
		{
			Dimension dim = (Dimension) obj;
			return new int[]{dim.width,dim.height};
		}
		if(obj instanceof Component)
		{
			Component comp = (Component) obj;
			return new int[]{comp.getWidth(),comp.getHeight()};
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
