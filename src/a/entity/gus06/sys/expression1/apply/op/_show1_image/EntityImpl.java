package a.entity.gus06.sys.expression1.apply.op._show1_image;

import a.framework.*;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160612";}


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
		
		if(obj instanceof byte[])		return new T1(findImage.t(obj));
		if(obj instanceof URL)			return new T1(findImage.t(obj));
		if(obj instanceof File)			return new T1(findImage.t(obj));
		if(obj instanceof Image)		return new T1(obj);
		if(obj instanceof RenderedImage)	return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		
		public T1(Object data)
		{this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return new E1(data,(String) obj);}
	}
	
	
	private class E1 implements E
	{
		private Object data;
		private String title;
		
		public E1(Object data, String title)
		{
			this.data = data;
			this.title = title;	
		}
		
		public void e() throws Exception
		{perform.v(title,data);}
	}
}
