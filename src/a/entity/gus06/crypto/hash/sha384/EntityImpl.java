package a.entity.gus06.crypto.hash.sha384;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151014";}

	public static final String ALGO = "SHA-384";
	
	private MessageDigest md;

	public EntityImpl() throws Exception
	{md = MessageDigest.getInstance(ALGO);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Null input object");
		
		if(obj instanceof byte[]) return digest(((byte[])obj));
		if(obj instanceof String) return digest(((String)obj).getBytes("UTF8"));
		if(obj instanceof InputStream) return digest((InputStream)obj);
		if(obj instanceof File) return digest(new FileInputStream((File)obj));
		if(obj instanceof URL) return digest(((URL) obj).openStream());
		
		throw new Exception("Invalid input: "+obj.getClass().getName());
	}
	
	
	
	private byte[] digest(InputStream in) throws Exception
	{
		byte[] buffer = new byte[8192];
		int length;
		md.reset();
		while((length=in.read(buffer))!=-1) md.update(buffer,0,length);
		in.close();
		return md.digest();
	}
	
	
	

	private byte[] digest(byte[] in) throws Exception
	{
		md.reset();
		md.update(in);
		return md.digest();
	}
}
