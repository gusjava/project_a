package a.entity.gus06.sys.apachehttp.m.get.json;

import a.framework.*;
import org.apache.http.client.methods.HttpGet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180323";}
	
	public static final String CONTENT_TYPE = "application/json";
	
	private Service execute;
	private Service buildGet;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.apachehttp.execute.tostring");
		buildGet = Outside.service(this,"gus06.sys.apachehttp.build.request.get");
	}
	
	public Object t(Object obj) throws Exception
	{
		HttpGet get = (HttpGet) buildGet.t(obj);
		get.addHeader("Content-Type",CONTENT_TYPE);
		return execute.t(get);
	}
}