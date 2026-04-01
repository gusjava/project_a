package a.entity.gus06.convert.stringtobytearray.base64;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141015";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return convert((String) obj);
	}

	private byte[] convert(String s) throws Exception
	{
		int mod = s.length()%4;
		if(mod==3) s += "=";
		else if(mod==2) s += "==";
		
		return Base64Coder.decode(s);
	}
}
