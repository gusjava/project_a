package a.entity.gus06.crypto.tool.encrypt.string.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170520";}


	private Service toHexa;
	
	public EntityImpl() throws Exception
	{
		toHexa = Outside.service(this,"gus06.tostring.bytetohexa");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		String data = (String) o[1];
		
		byte[] b0 = data.getBytes("UTF-8");
		byte[] b1 = (byte[]) t.t(b0);
		
		return toHexa.t(b1);
	}
}
