package a.entity.gus06.sys.expression1.apply.op._show2_text;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220613";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.frame.show2.image");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return new T1(obj);
		if(obj instanceof CharSequence)return new T1(obj.toString());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		
		public T1(Object data)
		{this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return new T2(data,(Integer) obj);}
	}
	
	private class T2 implements T
	{
		private Object data;
		private Integer width;
		
		public T2(Object data, Integer width)
		{
			this.data = data;
			this.width = width;
		}
		
		public Object t(Object obj) throws Exception
		{return new T3(data,width,(Integer) obj);}
	}
	
	private class T3 implements T
	{
		private Object data;
		private Integer width;
		private Integer height;
		
		public T3(Object data, Integer width, Integer height)
		{
			this.data = data;
			this.width = width;
			this.height = height;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(data,width,height,(String) obj);}
	}
	
	
	private class E1 implements E
	{
		private Object data;
		private Integer width;
		private Integer height;
		private String title;
		
		public E1(Object data, Integer width, Integer height, String title)
		{
			this.data = data;
			this.width = width;
			this.height = height;
			this.title = title;	
		}
		
		public void e() throws Exception
		{
			Dimension dim = new Dimension(width.intValue(),height.intValue());
			perform.p(new Object[]{data,dim,title});
		}
	}
}