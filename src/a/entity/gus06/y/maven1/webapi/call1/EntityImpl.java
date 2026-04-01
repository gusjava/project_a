package a.entity.gus06.y.maven1.webapi.call1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251214";}

	public static final String MAVEN_CENTRAL = "https://repo1.maven.org/maven2/";
	public static final int TIMEOUT = 30000;
	
	public static final String KEY_URL = "url";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_GROUPID = "groupId";
	public static final String KEY_ARTIFACTID = "artifactId";
	public static final String KEY_VERSION = "version";
	public static final String KEY_TYPE = "type";


	private Service xmlParser;

	public EntityImpl() throws Exception
	{
		xmlParser = Outside.service(this,"gus06.file.convert.xml.parser");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return handle((Map) obj);
		if(obj instanceof Object[]) return handle((Object[]) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object handle(Map map) throws Exception
	{
		String baseUrl = get(map, KEY_URL, MAVEN_CENTRAL);
		String username = get(map, KEY_USERNAME, null);
		String password = get(map, KEY_PASSWORD, null);
		String groupId = get(map, KEY_GROUPID, null);
		String artifactId = get(map, KEY_ARTIFACTID, null);
		String version = get(map, KEY_VERSION, null);
		String type = get(map, KEY_TYPE, "pom");
		
		if(!baseUrl.endsWith("/")) baseUrl = baseUrl+"/";
		String url = baseUrl + buildPath(groupId, artifactId, version, type);
		try
		{
			return execute(url, username, password);
		}
		catch(Exception e)
		{
			Map output = new HashMap();
			output.put("error", "Failed to fetch " + type + " from Maven Central: " + url);
			output.put("exception", e.toString());
			return output;
		}
	}
	
	private Object handle(Object[] o) throws Exception
	{
		if(o.length==3) return handle((String)o[0], (String)o[1], (String)o[2], "pom");
		if(o.length==4) return handle((String)o[0], (String)o[1], (String)o[2], (String)o[3]);
		throw new Exception("Wrong data number: "+o.length);
	}

	private Object handle(String groupId, String artifactId, String version, String type) throws Exception
	{
		if(groupId==null) throw new Exception("Invalid null groupId");
		if(artifactId==null) throw new Exception("Invalid null artifactId");
		if(version==null) throw new Exception("Invalid null version");
		
		String url = MAVEN_CENTRAL + buildPath(groupId, artifactId, version, type);
		try
		{
			return execute(url, null, null);
		}
		catch(Exception e)
		{
			Map output = new HashMap();
			output.put("error", "Failed to fetch " + type + " from Maven Central: " + url);
			output.put("exception", e.toString());
			return output;
		}
	}

	private Map execute(
			final String url, 
			final String username, 
			final String password) throws Exception
	{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<String> task = () -> execute_(url, username, password);
		Future<String> future = executor.submit(task);

		try
		{
			String result = future.get(TIMEOUT, TimeUnit.MILLISECONDS);
			return (Map) xmlParser.t(result);
		}
		catch(TimeoutException e)
		{
			future.cancel(true);
			Map output = new HashMap();
			output.put("error", "Request TIMEOUT reached for Maven Central call: " + TIMEOUT + " ms");
			return output;
		}
		finally
		{
			executor.shutdownNow();
		}
	}

	private String execute_(String url, String username, String password) throws Exception
	{
		RequestConfig config = RequestConfig.custom()
			.setConnectTimeout(TIMEOUT)
			.setConnectionRequestTimeout(TIMEOUT)
			.setSocketTimeout(TIMEOUT)
			.build();

		try (CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(config)
				.build()) {
			HttpGet request = new HttpGet(url);
			
			if(username!=null && password!=null)
			{
				String auth = username + ":" + password;
				String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
				request.setHeader("Authorization", "Basic " + encodedAuth);
			}
			
			try (CloseableHttpResponse response = httpClient.execute(request)) {
				int code = response.getStatusLine().getStatusCode();
				if(code == 200) return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
				throw new Exception("HTTP " + code + " : " + response.getStatusLine().getReasonPhrase());
			}
		}
	}
	
	private String buildPath(String groupId, String artifactId, String version, String type)
	{
		return groupId.replace('.', '/') + "/" + artifactId + "/" + version + "/" + artifactId + "-" + version + "." + type;
	}
	
	private String get(Map map, String key, String defaultValue)
	{return map.containsKey(key) ? (String) map.get(key) : defaultValue;}
}
