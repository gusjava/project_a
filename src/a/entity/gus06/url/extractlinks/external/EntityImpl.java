package a.entity.gus06.url.extractlinks.external;

import a.framework.*;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170326";}


	private Service findURL;
	private Service urlToText;
	private Service extractSrc;
	private Service extractHref;
	private Service decode;
	private Service rmScripts;

	public EntityImpl() throws Exception
	{
		findURL = Outside.service(this,"gus06.find.url");
		urlToText = Outside.service(this,"gus06.web.download.urltotext.utf8");
		extractSrc = Outside.service(this,"gus06.string.extract.html.src.a");
		extractHref = Outside.service(this,"gus06.string.extract.html.href.a");
		decode = Outside.service(this,"gus06.string.transform.format.html.decode");
		rmScripts = Outside.service(this,"gus06.string.transform.format.html.rm.scripts");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		URL url = (URL) findURL.t(obj);
		String host = url.getHost();
		
		String text = (String) urlToText.t(url);
		text = (String) rmScripts.t(text);
		
		List list1 = new ArrayList();
		
		List linksSrc = (List) extractSrc.t(text);
		for(int i=0;i<linksSrc.size();i++)
		{
			String link = (String) linksSrc.get(i);
			link = (String) decode.t(link);
			
			URL u = buildURL(url,link);
			if(u!=null && !u.getHost().equals(host)) list1.add(u);
		}
		
		List linksHref = (List) extractHref.t(text);
		for(int i=0;i<linksHref.size();i++)
		{
			String link = (String) linksHref.get(i);
			link = (String) decode.t(link);
			
			URL u = buildURL(url,link);
			if(u!=null && !u.getHost().equals(host)) list1.add(u);
		}
		
		return list1;
	}
	
	
	
	
	private URL buildURL(URL root, String link)
	{
		try{return new URL(root,link);}
		catch(Exception e)
		{
			String message = "Invalid href link: "+link+" (root="+root+")";
			Outside.err(this,"buildURL(URL,String)",new Exception(message,e));
			return null;
		}
	}
}
