package a.entity.gus06.web.openrouter.api.credits.call;

import a.framework.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251203";}

	public static final String URL = "https://openrouter.ai/api/v1/credits";

	private Service jsonParser;

	public EntityImpl() throws Exception
	{
		jsonParser = Outside.service(this, "gus06.file.convert.json.parser");
	}

	public Object t(Object obj) throws Exception
	{
		String apikey = (String) obj;

		HttpGet request = new HttpGet(URL);
		request.addHeader("Authorization", "Bearer " + apikey);
		request.addHeader("Accept", "application/json");

		String jsonOutput = execute(request);
		Object r = jsonParser.t(jsonOutput);
		if(r==null) throw new Exception("Failed to parse JSON ["+jsonOutput+"] (null returned)");
		return r;
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