package a.entity.gus06.web.openrouter.api.chat.call;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251126";}
	
	public static final String URL = "https://openrouter.ai/api/v1/chat/completions";
	public static final int DEFAULT_TIMEOUT = 300000;

	private Service toJson;
	private Service jsonParser;

	public EntityImpl() throws Exception
	{
		toJson = Outside.service(this,"gus.x.json.build1");
		jsonParser = Outside.service(this,"gus06.file.convert.json.parser");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==2) return handle((Map) o[0], (String) o[1], null);
		if(o.length==3) return handle((Map) o[0], (String) o[1], (Integer) o[2]);
		throw new Exception("Wrong data number: "+o.length);
	}
	
	private Object handle(Map input, String apikey, Integer timeout) throws Exception
	{
		String jsonInput = (String) toJson.t(input);
		if(timeout==null) timeout = DEFAULT_TIMEOUT;
		
		HttpPost request = new HttpPost(URL);
		request.addHeader("Authorization", "Bearer " + apikey);
		request.setHeader("Content-Type", "application/json");
		request.setEntity(new StringEntity(jsonInput, StandardCharsets.UTF_8));
		
		try{return execute(request, timeout);}
		catch(Exception e)
		{
			String message = "POST Request failed for JSON: "+jsonInput;
			throw new Exception(message, e);
		}
	}
	
	private Object execute(final HttpUriRequest request, final Integer timeout) throws Exception
	{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<String> task = () -> execute_(request, timeout);
		Future<String> future = executor.submit(task);
		
		try
		{
			String jsonOutput = future.get(timeout, TimeUnit.MILLISECONDS);
			return jsonParser.t(jsonOutput);
		}
		catch(TimeoutException e)
		{
			future.cancel(true);
			Map output = new HashMap();
			output.put("error", "Request timeout reached for model call: "+timeout+" ms");
			return output;
		}
		finally{executor.shutdownNow();}
	}
	
	private String execute_(HttpUriRequest request, Integer timeout) throws Exception
	{
		try (CloseableHttpClient httpClient = createClient(timeout)) {
			try (CloseableHttpResponse response = httpClient.execute(request)) {
				if (response.getStatusLine().getStatusCode() == 200)
					return EntityUtils.toString(response.getEntity());
				throw new Exception(response.getStatusLine().toString());
			} 
		}
	}
	
	private CloseableHttpClient createClient(Integer timeout) throws Exception
	{
		if(timeout==null) return HttpClients.createDefault();
		
		RequestConfig config = RequestConfig.custom()
			.setConnectTimeout(timeout)
			.setConnectionRequestTimeout(timeout)
			.setSocketTimeout(timeout)
			.build();
		
		return HttpClients.custom()
			.setDefaultRequestConfig(config)
			.build();
	}
}
