package a.entity.gus06.sys.apachehttp.tool.response.tostring;

import a.framework.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190706";}
	
	public Object t(Object obj) throws Exception
	{
		HttpResponse response = (HttpResponse) obj;
		HttpEntity httpEntity = response.getEntity();
		return EntityUtils.toString(httpEntity);
	}
}
