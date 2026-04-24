package a.entity.gus06.web.httprequest.post.download;

import a.framework.*;

import java.io.*;
import java.net.*;
import java.util.Map;


public class EntityImpl implements Entity, T, V {

	public String creationDate() {return "20150318";}
	
	public static final String METHOD_POST = "POST";


	private Service getFileName;
	private Service mapToString;
	private Service findUrl;
	
	private File storeDir;
	private Map map;
	private String cookie;
	
	
	public EntityImpl() throws Exception
	{
		getFileName = Outside.service(this,"gus06.web.httprequest.getfilename");
		mapToString = Outside.service(this,"gus.x.tostring.map.urlencoding");
		findUrl = Outside.service(this,"gus.y.find1.url");
		
		storeDir = (File) Outside.resource(this,"defaultdir");
	}

	

	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("map")) {map = (Map) obj;return;}
		if(key.equals("cookie")) {cookie = (String) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}



	public Object t(Object obj) throws Exception
	{
		URL url = (URL) findUrl.t(obj);
		if(map==null) throw new Exception("Parameter map has not been initialized yet");
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(METHOD_POST);
		con.setDoOutput(true);
		if(cookie!=null) con.setRequestProperty("Set-Cookie",cookie); 
		
		OutputStream os = con.getOutputStream();
		OutputStreamWriter wr = new OutputStreamWriter(os,"UTF-8");
		wr.write(buildInput());
		wr.flush();
		
		String fileName = (String) getFileName.t(con);
		File file = new File(storeDir,fileName);
		
		InputStream is = con.getInputStream();
		FileOutputStream fos = new FileOutputStream(file);  
        	
		int b;
        	while((b = is.read())!=-1)
        	{
        		fos.write(b);
        		Thread.yield();
        	}    
        	is.close();
        	fos.close();
        	con.disconnect();

        	if(file.length()==0) throw new Exception("Empty file for url: "+url);
		return file;
	}

	
	private String buildInput() throws Exception
	{return (String) mapToString.t(map);}
}
