package a.entity.gus06.web.download.filehandler;

import java.io.File;
import java.net.URL;
import a.framework.*;
import java.net.URLConnection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170202";}


	private Service findURL;
	private Service connect;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		findURL = Outside.service(this,"gus06.find.url");
		connect = Outside.service(this,"gus06.url.connect.as.mozilla50");
		perform = Outside.service(this,"gus06.web.download.urltofile.perform");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		URL url = (URL) findURL.t(o[0]);
		File file = (File) o[1];
		
		try
		{
			URLConnection con = (URLConnection) connect.t(url);
			perform.p(new Object[]{con,file});
			if(file.length()==0) throw new Exception("Empty file for url: "+url);
		}
		catch(Exception e)
		{
			String message = "url "+url+" failed to be downloaded into file "+file;
			throw new Exception(message,e);
		}
	}
}
