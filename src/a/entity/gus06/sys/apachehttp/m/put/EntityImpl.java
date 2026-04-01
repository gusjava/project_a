package a.entity.gus06.sys.apachehttp.m.put;

import a.framework.*;
import org.apache.http.client.methods.HttpPut;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190723";}

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
		return execute.t(put);
	}
}
