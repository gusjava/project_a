package a.entity.gus06.crypto.checksum.crc32.v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.CRC32;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160307";}



	public Object t(Object obj) throws Exception
	{
		if(obj instanceof byte[])
			return crc(((byte[])obj));
		if(obj instanceof String)
			return crc(((String)obj).getBytes());
		if(obj instanceof InputStream)
			return crc((InputStream)obj);
		if(obj instanceof File)
			return crc(new FileInputStream((File)obj));
		if(obj instanceof URL)
			return crc(((URL)obj).openStream());
		
		throw new Exception("Invalid input: "+obj.getClass().getName());
	}


	
	private String crc(InputStream in) throws IOException
	{
		CRC32 crc32 = new CRC32();
		byte[] buffer = new byte[8192];
		int length;
		while((length=in.read(buffer))!=-1)
		crc32.update(buffer, 0, length);
		in.close();
		return ""+crc32.getValue();
	}
	
	private String crc(byte[] in)
	{
		CRC32 crc32 = new CRC32();
		crc32.update(in);
		return ""+crc32.getValue();
	}
}
