package a.entity.gus06.tostring.desc.short1.renderedimage;

import a.framework.*;
import java.awt.image.RenderedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}



	public Object t(Object obj) throws Exception
	{
		RenderedImage m = (RenderedImage) obj;
		return className(m)+" ["+m.getWidth()+","+m.getHeight()+"]";
	}
	
	private String className(Object obj)
	{return obj.getClass().getSimpleName();}
}
