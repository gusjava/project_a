package a.entity.gus.x.messagedigest.md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20140704";}

	public static final String ALGO = "MD5";

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;

		if(obj instanceof byte[]) return digest(((byte[])obj));
		if(obj instanceof String) return digest(((String)obj).getBytes("UTF8"));
		if(obj instanceof InputStream) return digest((InputStream)obj);
		if(obj instanceof File) return digest(new FileInputStream((File)obj));
		if(obj instanceof URL) return digest(((URL) obj).openStream());

		throw new Exception("Invalid input: "+obj.getClass().getName());
	}

	private byte[] digest(InputStream in) throws Exception
	{
		MessageDigest md = MessageDigest.getInstance(ALGO);
		byte[] buffer = new byte[8192];
		int length;
		while((length=in.read(buffer))!=-1) md.update(buffer,0,length);
		in.close();
		return md.digest();
	}

	private byte[] digest(byte[] in) throws Exception
	{
		MessageDigest md = MessageDigest.getInstance(ALGO);
		md.update(in);
		return md.digest();
	}
}
