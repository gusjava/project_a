package a.entity.gus06.y.maven1.html.crawler;

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

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251218";}
	
	public static final String MAVEN_CENTRAL = "https://repo1.maven.org/maven2/";
	public static final Pattern P = Pattern.compile("<a href=\"([^\"]+)\"");
	
	public static final String KEY_URL = "url";
	public static final String KEY_PATH = "path";


	private Service urlToText;

	public EntityImpl() throws Exception
	{
		urlToText = Outside.service(this,"gus06.web.download.urltotext");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return crawl(MAVEN_CENTRAL, (String) obj);
		if(obj instanceof Object[]) return crawl((Object[]) obj);
		if(obj instanceof Map) return crawl((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private List crawl(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return crawl((String) o[0], (String) o[1]);
	}
	
	private List crawl(Map map) throws Exception
	{
		String url = get(map, KEY_URL);
		String path = get(map, KEY_PATH);
		
		return crawl(url, path);
	}
	
	private List crawl(String baseUrl, String path) throws Exception
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

		Matcher m = P.matcher(page);
		List result = new ArrayList();
		while(m.find())
		{
			String name = m.group(1);
			if(!name.equals("../") && name.endsWith("/"))
			{
				name = name.replace("/","");
				String fullPath = path!=null && !path.equals("") ? path+"/"+name : name;
				result.add(fullPath);
			}
		}
		return result;
	}
	
	private String get(Map map, String key)
	{
		if(map.containsKey(key)) return (String) map.get(key);
		return null;
	}
}
