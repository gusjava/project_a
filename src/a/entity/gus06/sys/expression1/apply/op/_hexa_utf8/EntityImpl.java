package a.entity.gus06.sys.expression1.apply.op._hexa_utf8;

import a.framework.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160306";}
	
	public final static Charset CHARSET = Charset.forName("UTF-8");
	
	
	private Service hexaToByte;
	
	public EntityImpl() throws Exception
	{
		hexaToByte = Outside.service(this,"gus06.convert.stringtobytearray.hexa");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String)
		{
			byte[] ba = (byte[]) hexaToByte.t(obj);
			return new String(ba,CHARSET);
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
