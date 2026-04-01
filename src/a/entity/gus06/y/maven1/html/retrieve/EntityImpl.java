package a.entity.gus06.y.maven1.html.retrieve;

import a.framework.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251221";}
	
	public static final String MAVEN_CENTRAL = "https://repo1.maven.org/maven2/";
	public static final Pattern P = Pattern.compile("<a href=\"([^\"]+)\"");
	
	public static final String KEY_URL = "url";
	public static final String KEY_PATH = "path";


	private Service urlToText;
	private Service urlToExt;
	private Service retrievePom;
	private Service retrieveXml;

	public EntityImpl() throws Exception
	{
		urlToText = Outside.service(this,"gus06.web.download.urltotext");
		urlToExt = Outside.service(this,"gus06.url.getextension");
		retrievePom = Outside.service(this,"gus06.y.maven1.html.pom");
		retrieveXml = Outside.service(this,"gus06.y.maven1.html.xml");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return retrieve(MAVEN_CENTRAL, (String) obj);
		if(obj instanceof Object[]) return retrieve((Object[]) obj);
		if(obj instanceof Map) return retrieve((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Map retrieve(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return retrieve((String) o[0], (String) o[1]);
	}
	
	private Map retrieve(Map map) throws Exception
	{
		String url = get(map, KEY_URL);
		String path = get(map, KEY_PATH);
		
		return retrieve(url, path);
	}
	
	private Map retrieve(String baseUrl, String path) throws Exception
	{
		if(baseUrl==null) baseUrl = MAVEN_CENTRAL;
		StringBuilder sb = new StringBuilder(baseUrl);
		if(!baseUrl.endsWith("/")) sb.append("/");
		
		if(path!=null && !path.equals(""))
		{
			sb.append(path);
			sb.append("/");
		}
		
		String url = sb.toString();

		String page = (String) urlToText.t(url);
		Map links = extractLinks(page, url);
		
		
		if(links.containsKey("pom"))
		{
			String link = (String) links.get("pom");
			return (Map) retrievePom.t(link);
		}
		if(links.containsKey("xml"))
		{
			String link = (String) links.get("xml");
			return (Map) retrieveXml.t(link);
		}
		
		return links;
	}
	
	
	private Map extractLinks(String page, String url) throws Exception
	{
		if(!url.endsWith("/")) url+="/";
		
		Matcher m = P.matcher(page);
		Map map = new HashMap();
		while(m.find())
		{
			String href = m.group(1);
			if(!href.contains("/"))
			{
				String fullPath = url+href;
				String ext = (String) urlToExt.t(fullPath);
				map.put(ext, fullPath);
			}
		}
		return map;
	}
	
	private String get(Map map, String key)
	{
		if(map.containsKey(key)) return (String) map.get(key);
		return null;
	}
}
