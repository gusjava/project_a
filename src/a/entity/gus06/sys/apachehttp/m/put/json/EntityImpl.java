package a.entity.gus06.sys.apachehttp.m.put.json;

import a.framework.*;
import org.apache.http.client.methods.HttpPut;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190723";}
	
	public static final String CONTENT_TYPE = "application/json";
	
	private Service execute;
	private Service buildPut;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.apachehttp.execute.tostring");
		buildPut = Outside.service(this,"gus06.sys.apachehttp.build.request.put");
	}
	
	public Object t(Object obj) throws Exception
	{
		HttpPut put = (HttpPut) buildPut.t(obj);
		put.addHeader("Content-Type",CONTENT_TYPE);
		return execute.t(put);
	}
}