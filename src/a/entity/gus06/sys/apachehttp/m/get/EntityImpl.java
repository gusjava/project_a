package a.entity.gus06.sys.apachehttp.m.get;

import a.framework.*;
import org.apache.http.client.methods.HttpGet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180323";}

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
		return execute.t(get);
	}
}
