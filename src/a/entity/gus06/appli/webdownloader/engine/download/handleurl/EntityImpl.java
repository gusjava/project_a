package a.entity.gus06.appli.webdownloader.engine.download.handleurl;

import a.framework.*;
import java.net.URL;
import java.io.File;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150408";}

	public static final String KEY_STOREDIR = "storedir";
	

	private Service optionManager;
	private Service normalizeName;
		
	public EntityImpl() throws Exception
	{
		optionManager = Outside.service(this,"gus06.sys.option.manager");
		normalizeName = Outside.service(this,"gus06.string.transform.normalize.filename");
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		URL url = (URL) obj;
		
		File storeDir = new File(option(KEY_STOREDIR));
		storeDir.mkdirs();
		
		String fileName = urlToFilename(url);
		if(!url.getFile().contains(".")) fileName = fileName+".html";
		
		File file = new File(storeDir,fileName);
		
		download(file,url);
		return url.toString();
	}
	
	
	
	
	private String option(String key) throws Exception
	{
		String v = (String) optionManager.r(key);
		if(v==null) throw new Exception("Undefined option: "+key);
		return v;
	}
	
	
	private String urlToFilename(URL url) throws Exception
	{return (String) normalizeName.t(url.toString());}
	
	
	
	
	
	private void download(File file, URL url) throws Exception
	{
	    	try
    		{
        		URLConnection con = url.openConnection();
        		con.connect();

        		FileOutputStream fos = new FileOutputStream(file);  
        		InputStream is = con.getInputStream(); 

			byte[] buffer = new byte[4096];
			int n = - 1;
			
        		while((n = is.read(buffer)) != -1)
			{if(n > 0) fos.write(buffer,0,n);}
			
        		is.close();
        		fos.close();

        		if(file.length()==0) throw new Exception("Empty file for url: "+url);
    		}
    		catch(Exception e)
    		{
    			String message = "url "+url+" failed to be downloaded into file "+file;
    			throw new Exception(message,e);
    		}
	}
}
