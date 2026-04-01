package a.entity.gus06.sys.apachehttp.m.patch;

import a.framework.*;
import org.apache.http.client.methods.HttpPatch;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190725";}

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
		return execute.t(patch);
	}
}
