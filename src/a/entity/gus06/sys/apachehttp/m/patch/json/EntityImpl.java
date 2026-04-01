package a.entity.gus06.sys.apachehttp.m.patch.json;

import a.framework.*;
import org.apache.http.client.methods.HttpPatch;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190725";}
	
	public static final String CONTENT_TYPE = "application/json";
	
	private Service execute;
	private Service buildPatch;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.apachehttp.execute.tostring");
		buildPatch = Outside.service(this,"gus06.sys.apachehttp.build.request.patch");
	}
	
	public Object t(Object obj) throws Exception
	{
		HttpPatch patch = (HttpPatch) buildPatch.t(obj);
		patch.addHeader("Content-Type",CONTENT_TYPE);
		return execute.t(patch);
	}
}