package a.entity.gus06.string.transform.url.urltohttpcode;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190712";}


	private Service urlToCode;

	public EntityImpl() throws Exception
	{urlToCode = Outside.service(this,"gus06.sys.apachehttp.m.get.statuscode");}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++) handle(b,n[i]);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private void handle(StringBuffer b, String line)
	{
		try
		{
			URL url = new URL(line);
			b.append(urlToCode(url)+"\n");
		}
		catch(Exception e)
		{b.append(line+"\n");}
	}
	
	
	private String urlToCode(URL url) throws Exception
	{return (String) urlToCode.t(url);}
}
