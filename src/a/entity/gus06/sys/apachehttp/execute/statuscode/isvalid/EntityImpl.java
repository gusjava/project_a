package a.entity.gus06.sys.apachehttp.execute.statuscode.isvalid;

import a.framework.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.HttpClient;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190712";}


	private Service buildStatusCode;

	public EntityImpl() throws Exception
	{
		buildStatusCode = Outside.service(this,"gus06.sys.apachehttp.execute.statuscode");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Integer code = (Integer) buildStatusCode.t(obj);
		return code!=null && isValidStatusCode(code);
	}
	
	private boolean isValidStatusCode(int code)
	{return code==200 || code==201;}
}
