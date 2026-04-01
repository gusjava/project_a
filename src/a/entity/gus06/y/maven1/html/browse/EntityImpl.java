package a.entity.gus06.y.maven1.html.browse;

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

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251221";}
	
	public static final String MAVEN_CENTRAL = "https://repo1.maven.org/maven2/";
	
	public static final String KEY_URL = "url";
	public static final String KEY_PATH = "path";

	private Service browseUrl;

	public EntityImpl() throws Exception
	{
		browseUrl = Outside.service(this,"gus06.awt.desktop.browse");
	}

	public void p(Object obj) throws Exception
	{
		if(obj instanceof String)
		{browse(MAVEN_CENTRAL, (String) obj); return;}
		
		if(obj instanceof Object[])
		{browse((Object[]) obj); return;}
		
		if(obj instanceof Map)
		{browse((Map) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void browse(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		browse((String) o[0], (String) o[1]);
	}
	
	private void browse(Map map) throws Exception
	{
		String url = get(map, KEY_URL);
		String path = get(map, KEY_PATH);
		
		browse(url, path);
	}
	
	private void browse(String baseUrl, String path) throws Exception
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
		browseUrl.p(url);
	}
	
	private String get(Map map, String key)
	{
		if(map.containsKey(key)) return (String) map.get(key);
		return null;
	}
}
