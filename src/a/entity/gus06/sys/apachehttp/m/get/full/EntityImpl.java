package a.entity.gus06.sys.apachehttp.m.get.full;

import a.framework.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.Header;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191028";}

	private Service execute;
	private Service buildGet;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.apachehttp.execute.full");
		buildGet = Outside.service(this,"gus06.sys.apachehttp.build.request.get");
	}
	
	public Object t(Object obj) throws Exception
	{
		HttpGet get = (HttpGet) buildGet.t(obj);
		return execute.t(get);
	}
}
