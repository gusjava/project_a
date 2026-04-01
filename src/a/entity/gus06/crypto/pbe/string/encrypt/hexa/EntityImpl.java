package a.entity.gus06.crypto.pbe.string.encrypt.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150625";}

	
	private Service encrypt;
	private Service wrapper;
	
	public EntityImpl() throws Exception
	{
		encrypt = Outside.service(this,"gus06.crypto.pbe.bytearray.encrypt");
		wrapper = Outside.service(this,"gus06.crypto.tool.encrypt.string.hexa");
	}

	public Object t(Object obj) throws Exception
	{
		T t = (T) encrypt.t(obj);
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
