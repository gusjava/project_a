package a.entity.gus06.crypto.pbe.object.decrypt.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170520";}


	private Service decrypt;
	private Service wrapper;
	
	public EntityImpl() throws Exception
	{
		decrypt = Outside.service(this,"gus06.crypto.pbe.bytearray.decrypt");
		wrapper = Outside.service(this,"gus06.crypto.tool.decrypt.object.hexa");
	}

	public Object t(Object obj) throws Exception
	{
		T t = (T) decrypt.t(obj);
		return new Holder(t);
	}
	
	private class Holder implements T
	{
		private T t;
		public Holder(T t) {this.t = t;}
		
		public Object t(Object obj) throws Exception
		{return wrapper.t(new Object[]{t,obj});}
	}
}
