package a.entity.gus06.string.transform.url.urltotext.autodetect;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250201";}


	private Service urlToText;

	public EntityImpl() throws Exception
	{urlToText = Outside.service(this,"gus06.web.download.urltotext.autodetect");}
	
	
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
			URL url = new URL(line.trim());
			b.append(urlToText(url)+"\n");
		}
		catch(Exception e)
		{b.append(line+"\n");}
	}
	
	
	private String urlToText(URL url) throws Exception
	{return (String) urlToText.t(url);}
}