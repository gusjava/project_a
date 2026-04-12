package a.entity.gus06.web.openrouter.api.models.call;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251127";}
	
	public static final String URL = "https://openrouter.ai/api/v1/models";

	private Service jsonParser;

	public EntityImpl() throws Exception
	{
		jsonParser = Outside.service(this,"gus06.file.convert.json.parser");
	}
	
	public Object t(Object obj) throws Exception
	{
		String apikey = (String) obj;
		
		HttpGet request = new HttpGet(URL);
		request.addHeader("Authorization", "Bearer " + apikey);
		request.addHeader("Accept", "application/json");
		
		String jsonOutput = execute(request);
		return jsonParser.t(jsonOutput);
	}
	
	private String execute(HttpUriRequest request) throws Exception
	{
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			try (CloseableHttpResponse response = httpClient.execute(request)) {
				String body = EntityUtils.toString(response.getEntity());
				if (response.getStatusLine().getStatusCode() == 200) return body;
				throw new Exception(response.getStatusLine() + " - " + body);
			} 
		}
	}
}