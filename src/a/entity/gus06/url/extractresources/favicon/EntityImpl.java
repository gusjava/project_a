package a.entity.gus06.url.extractresources.favicon;

import a.framework.*;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201207";}


	private Service findURL;
	private Service urlToText;
	private Service extract;
	private Service findInfos;

	public EntityImpl() throws Exception
	{
		findURL = Outside.service(this,"gus06.find.url");
		urlToText = Outside.service(this,"gus06.web.download.urltotext.utf8");
		extract = Outside.service(this,"gus06.string.extract.html.tag.type.link.a");
		findInfos = Outside.service(this,"gus06.string.html.element.infomap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		URL url = (URL) findURL.t(obj);
		String host = url.getHost();
		
		String text = (String) urlToText.t(url);
		List list = (List) extract.t(text);
		
		Map map = new HashMap();
		for(int i=0;i<list.size();i++)
		{
			String link = (String) list.get(i);
			Map infos = (Map) findInfos.t(link);
			
			Map attrs = (Map) infos.get("attrs");
			if(attrs!=null)
			{
				String rel = get(attrs,"rel");
				String sizes = get(attrs,"sizes");
				String href = get(attrs,"href");
				
				if(Objects.equals(rel,"icon") || Objects.equals(rel,"shortcut icon"))
				map.put(sizes,href);
			}
		}
		
		if(map.isEmpty()) return null;
		if(map.size()==1)
		{
			String key = (String) map.keySet().iterator().next();
			return buildURL(url,(String) map.get(key));
		}
		
		if(map.containsKey("16x16")) return buildURL(url,(String) map.get("16x16"));
		if(map.containsKey("32x32")) return buildURL(url,(String) map.get("32x32"));
		if(map.containsKey("48x48")) return buildURL(url,(String) map.get("48x48"));
					
		return null;
	}
	
	
	
	private URL buildURL(URL base, String href) throws Exception
	{
		if(href.contains("?")) href = href.split("\\?")[0];
		
		if(href.startsWith("http://")) return new URL(href);
		if(href.startsWith("https://")) return new URL(href);
		
		if(!href.startsWith("/")) href = "/"+href;
		String root = base.getProtocol()+"://"+base.getHost();
		
		return new URL(root+href);
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}