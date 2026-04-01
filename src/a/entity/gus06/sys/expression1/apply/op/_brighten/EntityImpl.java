package a.entity.gus06.sys.expression1.apply.op._brighten;

import a.framework.*;
import java.awt.image.RenderedImage;
import java.awt.Image;
import javax.swing.Icon;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180330";}


	private Service performBuffered;
	private Service performRendered;
	
	public EntityImpl() throws Exception
	{
		performBuffered = Outside.service(this,"gus06.awt.bufferedimage.transform.color.brighten");
		performRendered = Outside.service(this,"gus06.awt.renderedimage.transform.color.brighten");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Icon)			return performBuffered.t(obj);
		if(obj instanceof Image)		return performBuffered.t(obj);
		if(obj instanceof RenderedImage)	return performRendered.t(obj);
		
		if(obj instanceof Color)		return ((Color) obj).brighter();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
