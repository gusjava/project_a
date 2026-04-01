package a.entity.gus06.web.allocine.api.tool.encrypt;

import a.framework.*;
import java.net.URLEncoder;
import java.security.MessageDigest;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200112";}

	public static final String SECRET_KEY1 = "29d185d98c984a359e6e6f26a0474269";
	public static final String SECRET_KEY = "1a1ed8c1bed24d60ae3472eed1da33eb";
	
	private MessageDigest md = MessageDigest.getInstance("SHA1"); 
	
	
	private Service buildBase64;
	private Service findSecretKey;
	
	public EntityImpl() throws Exception
	{
		buildBase64 = Outside.service(this,"gus06.tostring.bytetobase64");
		findSecretKey = Outside.service(this,"gus06.web.allocine.api.tool.secretkey");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String method = (String) o[0];
		String params = (String) o[1];
		
		String secretKey = (String) findSecretKey.g();
		
		return encrypt(method+params+secretKey);
	}
	
	private String encrypt(String str) throws Exception
	{
		md.update(str.getBytes());
		byte[] raw = md.digest();
		md.reset();
		return encode(buildBase64(raw));
	}
	
	private String buildBase64(byte[] raw) throws Exception
	{return (String) buildBase64.t(raw);}
	
	private String encode(String s) throws Exception
	{return URLEncoder.encode(s,"UTF-8");}
}
