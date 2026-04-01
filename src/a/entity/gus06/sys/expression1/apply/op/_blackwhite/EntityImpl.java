package a.entity.gus06.sys.expression1.apply.op._blackwhite;

import a.framework.*;
import java.awt.image.RenderedImage;
import java.awt.Image;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.bufferedimage.transform.color.blackandwhite");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Icon)			return perform.t(obj);
		if(obj instanceof Image)		return perform.t(obj);
		if(obj instanceof RenderedImage)	return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
