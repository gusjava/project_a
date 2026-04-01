package a.entity.gus06.crypto.checksum.crc16.xmodem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
		
		throw new Exception("Invalid input type: "+obj.getClass().getName());
	}
	
	
	private String crc(byte[] in)
	{
		int crc = 0x0000;
		
		for(int i=0;i<in.length;i++)
		{
			int c =((crc>>8) ^ in[i])<<8;
			for(int j=0;j<=7;j++) 
			{ 
				if((c & 0x8000)!=0) c = (c<<1)^0x1021;
				else c = c<<1;
			}
			crc = c^(crc<<8);
		}
		crc = crc & 0xFFFF;
		return ""+crc;
	}
	
	
	private String crc(InputStream in) throws IOException
	{
		int crc = 0x0000;
		int b;
		while((b=in.read())!=-1)
		{
			int c =((crc>>8) ^ b)<<8;
			for(int j=0;j<=7;j++) 
			{ 
				if((c & 0x8000)!=0) c = (c<<1)^0x1021;
				else c = c<<1;
			}
			crc = c^(crc<<8);
		}
		crc = crc & 0xFFFF;
		in.close();
		return ""+crc;
	}
}
