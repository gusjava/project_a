package a.entity.gus06.web.ollama.api.generate.call;

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

	public String creationDate() {return "20251206";}

	public static final String URL = "http://localhost:11434/api/generate";
	public static final int TIMEOUT = 300000;

	private Service toJson;
	private Service jsonParser;

	public EntityImpl() throws Exception
	{
		toJson = Outside.service(this,"gus.x.json.build1");
		jsonParser = Outside.service(this,"gus06.file.convert.json.parser");
	}

	public Object t(Object obj) throws Exception
	{
		Map input = (Map) obj;
		String jsonInput = (String) toJson.t(input);

		HttpPost request = new HttpPost(URL);
		request.setHeader("Content-Type", "application/json");
		request.setEntity(new StringEntity(jsonInput, StandardCharsets.UTF_8));

		try{return execute(request);}
		catch(Exception e)
		{
			String message = "POST Request failed for JSON: "+jsonInput;
			throw new Exception(message, e);
		}
	}

	private Object execute(final HttpUriRequest request) throws Exception
	{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<String> task = () -> execute_(request);
		Future<String> future = executor.submit(task);

		try
		{
			String jsonOutput = future.get(TIMEOUT, TimeUnit.MILLISECONDS);
			return jsonParser.t(jsonOutput);
		}
		catch(TimeoutException e)
		{
			future.cancel(true);
			Map output = new HashMap();
			output.put("error", "Request timeout reached for model call: "+TIMEOUT+" ms");
			return output;
		}
		finally{executor.shutdownNow();}
	}

	private String execute_(HttpUriRequest request) throws Exception
	{
		try (CloseableHttpClient httpClient = createClient()) {
			try (CloseableHttpResponse response = httpClient.execute(request)) {
				if (response.getStatusLine().getStatusCode() == 200)
					return EntityUtils.toString(response.getEntity());
				throw new Exception(response.getStatusLine().toString());
			} 
		}
	}

	private CloseableHttpClient createClient() throws Exception
	{
		RequestConfig config = RequestConfig.custom()
			.setConnectTimeout(TIMEOUT)
			.setConnectionRequestTimeout(TIMEOUT)
			.setSocketTimeout(TIMEOUT)
			.build();

		return HttpClients.custom()
			.setDefaultRequestConfig(config)
			.build();
	}
}