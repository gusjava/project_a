package a.entity.gus06.io.transfer.tobytearray;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140809";}
	
	
	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		InputStreamReader isr = null;
		BufferedReader br = null;
		
		int nRead;
		byte[] data = new byte[16384];

		try
		{
			while((nRead = is.read(data,0,data.length)) != -1)
			bos.write(data,0,nRead);
			bos.flush();
		}
		finally
		{is.close();}
		return bos.toByteArray();
	}
}
