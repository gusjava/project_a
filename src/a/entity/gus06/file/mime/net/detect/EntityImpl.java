package a.entity.gus06.file.mime.net.detect;

import a.framework.*;
import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161128";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return detectFromFile((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String detectFromFile(File f) throws IOException
	{
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		URL url = f.toURI().toURL();
      		return fileNameMap.getContentTypeFor(url.toString());
	}
}
