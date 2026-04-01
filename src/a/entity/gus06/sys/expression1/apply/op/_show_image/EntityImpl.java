package a.entity.gus06.sys.expression1.apply.op._show_image;

import a.framework.*;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160412";}


	private Service perform;
	private Service findImage;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.frame.show.image");
		findImage = Outside.service(this,"gus06.image.find");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof byte[])		return new E1(findImage.t(obj));
		if(obj instanceof URL)			return new E1(findImage.t(obj));
		if(obj instanceof File)			return new E1(findImage.t(obj));
		if(obj instanceof Icon)			return new E1(findImage.t(obj));
		
		if(obj instanceof Image)		return new E1(obj);
		if(obj instanceof RenderedImage)	return new E1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o) {this.o = o;}
		
		public void e() throws Exception
		{perform.p(o);}
	}
}
