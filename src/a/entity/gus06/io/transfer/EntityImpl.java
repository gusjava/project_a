package a.entity.gus06.io.transfer;

import a.framework.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.ByteBuffer;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140705";}

	
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[])obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);

		InputStream is = (InputStream) o[0];
		OutputStream os = (OutputStream) o[1];
		
		transfer2(is,os);
	}
        


	// m�thode 1
	
	public static int BUFFER = 2048;
	
	private void transfer1(InputStream is, OutputStream os) throws Exception
	{
		try
		{
			int b;
			byte buff[] = new byte[BUFFER];
			while((b = is.read(buff,0,BUFFER)) != -1) os.write(buff,0,b);
		}
		finally
		{
			is.close();
			os.close();
		}
	}
	
	
	
	
	
	// m�thode 2
	
	private void transfer2(InputStream is, OutputStream os) throws Exception
	{
		ReadableByteChannel rbc = null;
		WritableByteChannel wbc = null;
		
		try
		{
			rbc = Channels.newChannel(is);
			wbc = Channels.newChannel(os);
		
			ByteBuffer buffer = ByteBuffer.allocateDirect(32 * 1024);

			while(rbc.read(buffer) != -1 || buffer.position() > 0)
			{
				buffer.flip();
				wbc.write(buffer);
				buffer.compact();
			}
		}
		finally
		{
			if(rbc!=null) rbc.close();
			if(wbc!=null) wbc.close();
		}
	}
}
