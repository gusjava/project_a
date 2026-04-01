package a.entity.gus06.sys.apachehttp.execute.response;

import a.framework.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.HttpClient;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190706";}


	private Service buildClient;

	public EntityImpl() throws Exception
	{
		buildClient = Outside.service(this,"gus06.sys.apachehttp.build.client");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		HttpRequestBase request = (HttpRequestBase) obj;
		
		HttpClient client = (HttpClient) buildClient.g();
		HttpResponse response = client.execute(request);
		
		return response;
	}
}
