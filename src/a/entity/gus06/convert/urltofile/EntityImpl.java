package a.entity.gus06.convert.urltofile;

import a.framework.*;
import java.net.*;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140909";}


	private Service tmpFile;
	private Service transfert;
	private Service normalizeName;


	public EntityImpl() throws Exception
	{
		tmpFile = Outside.service(this,"gus06.file.tmpfile");
		transfert = Outside.service(this,"gus06.io.transfer");
		normalizeName = Outside.service(this,"gus06.string.transform.normalize.filename");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		URL url = (URL) obj;
		String fileName = (String) normalizeName.t(url.getFile());
		File file = (File) tmpFile.t("file");
		
		try
		{
			HttpURLConnection con = (HttpURLConnection) rebuildURL(url).openConnection();
			con.connect();

			FileOutputStream fos = new FileOutputStream(file);  
			InputStream is = con.getInputStream(); 

			transfert.p(new Object[]{is,fos});
			con.disconnect();

			if(file.length()==0) throw new Exception("Empty file for url: "+url);
		}
		catch(Exception e)
		{
			String message = "url "+url+" failed to be downloaded into file "+file;
			throw new Exception(message,e);
		}
		return file;
	}
	
	
	
	
	private URL rebuildURL(URL url) throws Exception
	{
		String s = url.toString();
		return new URL(s.replace(" ","%20"));
	}
}
