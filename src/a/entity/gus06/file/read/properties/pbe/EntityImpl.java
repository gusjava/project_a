package a.entity.gus06.file.read.properties.pbe;

import a.framework.*;

public class EntityImpl implements Entity, T, V {

	public String creationDate() {return "20150613";}

	
	private Service decrypt;
	private Service readProp;
	
	private T decrypter;
	
	
	public EntityImpl() throws Exception
	{
		decrypt = Outside.service(this,"gus06.crypto.pbe.object.decrypt.hexa");
		readProp = Outside.service(this,"gus06.file.read.properties");
	}

	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("password")) {decrypter = toDecrypted(obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	private T toDecrypted(Object obj) throws Exception
	{
		if(obj instanceof T) return (T) obj;
		if(obj instanceof String) return (T) decrypt.t((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}

	
	public Object t(Object obj) throws Exception
	{
		if(decrypter==null) throw new Exception("Decrypter not initialized yet");
		Object prop = readProp.t(obj);
		return decrypter.t(prop);
	}
}
