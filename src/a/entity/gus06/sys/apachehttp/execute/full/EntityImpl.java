package a.entity.gus06.sys.apachehttp.execute.full;

import a.framework.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.Header;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191028";}


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
			
			Header[] headers = response.getAllHeaders();
			Map headerMap = new HashMap();
			for(Header header : headers)
			{
				String name = header.getName();
				String value = header.getValue();
				headerMap.put(name,value);
			}
			
			int code = response.getStatusLine().getStatusCode();
			String body = (String) toString.t(response);
			
			Map m = new HashMap();
			m.put("out_code",""+code);
			m.put("out_body",body);
			m.put("out_header",headerMap);
			
			return m;
		}
		finally
		{
			request.releaseConnection();
		}
	}
}
