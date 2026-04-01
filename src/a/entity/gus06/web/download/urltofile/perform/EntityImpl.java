package a.entity.gus06.web.download.urltofile.perform;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import a.framework.*;
import java.io.IOException;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170202";}



	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		URLConnection con = (URLConnection) o[0];
		File file = (File) o[1];
		
		InputStream is = null;
		FileOutputStream fos = null;
		
		try
		{
			fos = new FileOutputStream(file);  
			is = con.getInputStream(); 

			int b;
			while((b = is.read())!=-1)
			{
				fos.write(b);
				Thread.yield();
			}
		}
		finally
		{
			if(is!=null) try{is.close();}catch(IOException e){}
			if(fos!=null) try{fos.close();}catch(IOException e){}
			
			if(con instanceof HttpURLConnection)
			((HttpURLConnection)con).disconnect();
		}
	}
}
