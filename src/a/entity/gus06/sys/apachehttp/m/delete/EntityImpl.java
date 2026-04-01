package a.entity.gus06.sys.apachehttp.m.delete;

import a.framework.*;
import org.apache.http.client.methods.HttpDelete;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190830";}

	private Service execute;
	private Service buildDelete;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.apachehttp.execute.tostring");
		buildDelete = Outside.service(this,"gus06.sys.apachehttp.build.request.delete");
	}
	
	public Object t(Object obj) throws Exception
	{
		HttpDelete delete = (HttpDelete) buildDelete.t(obj);
		return execute.t(delete);
	}
}
