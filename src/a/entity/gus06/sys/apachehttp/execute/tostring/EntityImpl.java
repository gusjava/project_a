package a.entity.gus06.sys.apachehttp.execute.tostring;

import a.framework.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190706";}


	private Service execute;
	private Service toString;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.apachehttp.execute.response");
		toString = Outside.service(this,"gus06.sys.apachehttp.tool.response.tostring");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		HttpRequestBase request = (HttpRequestBase) obj;
		
		try
		{
			HttpResponse response = (HttpResponse) execute.t(request);
			return toString.t(response);
		}
		finally
		{
			request.releaseConnection();
		}
	}
}
