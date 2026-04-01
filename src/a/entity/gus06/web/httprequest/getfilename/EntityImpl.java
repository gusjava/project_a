package a.entity.gus06.web.httprequest.getfilename;

import a.framework.*;
import java.net.HttpURLConnection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150319";}


	private Service normalizeName;
	
	public EntityImpl() throws Exception
	{normalizeName = Outside.service(this,"gus06.string.transform.normalize.filename");}
	
	
	public Object t(Object obj) throws Exception
	{return findFileName((HttpURLConnection) obj);}
	
	
	
	private String findFileName(HttpURLConnection con) throws Exception
	{
		String name = fileNameFromHeader(con);
		if(name!=null) return normalizeName(name);
		name = con.getURL().getFile();
		return normalizeName(name);
	}
	
	private String fileNameFromHeader(HttpURLConnection con)
	{
		String raw = con.getHeaderField("Content-Disposition");
		if(raw == null) return null;
		if(raw.indexOf("=") == -1) return null;
		
		raw = raw.split("=")[1];
		return raw.replace("\"","").trim();
	}
	
	private String normalizeName(String name) throws Exception
	{return (String) normalizeName.t(name);}
}
