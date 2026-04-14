package a.entity.gus06.web.chatgpt.api.call;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250531";}
	
	public static final String URL = "https://api.openai.com/v1/chat/completions";

	private Service toJson;
	private Service jsonParser;

	public EntityImpl() throws Exception
	{
		toJson = Outside.service(this,"gus.x.json.build1");
		jsonParser = Outside.service(this,"gus.x.json.parse1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		String apikey = (String) o[1];
		
		String json = (String) toJson.t(input);
		
		HttpPost request = new HttpPost(URL);
		request.addHeader("Authorization", "Bearer " + apikey);
		request.setHeader("Content-Type", "application/json");
		request.setEntity(new StringEntity(json, "UTF-8"));
		
		String jsonOutput = execute(request);
		return jsonParser.t(jsonOutput);
	}
	
	
	private String execute(HttpUriRequest request) throws Exception
	{
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			try (CloseableHttpResponse response = httpClient.execute(request)) {
				if (response.getStatusLine().getStatusCode() == 200)
					return EntityUtils.toString(response.getEntity());
				throw new Exception(response.getStatusLine().toString());
			} 
		}
	}
}