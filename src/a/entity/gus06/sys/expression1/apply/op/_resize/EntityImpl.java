package a.entity.gus06.sys.expression1.apply.op._resize;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.Image;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180323";}
	


	private Service resize;
	private Service toImage;
	
	public EntityImpl() throws Exception
	{
		resize = Outside.service(this,"gus06.awt.bufferedimage.resize");
		toImage = Outside.service(this,"gus06.find.bufferedimage");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof BufferedImage)	return new T1(obj);
		if(obj instanceof RenderedImage)	return new T1(toImage.t(obj));
		if(obj instanceof Image)		return new T1(toImage.t(obj));
		if(obj instanceof Icon)			return new T1(toImage.t(obj));
		if(obj instanceof byte[])		return new T1(toImage.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return resize.t(new Object[]{data,obj});}
	}
}
