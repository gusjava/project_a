package a.entity.gus06.sys.apachehttp.m.post.statuscode;

import a.framework.*;
import org.apache.http.client.methods.HttpPost;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190729";}

	private Service execute;
	private Service buildPost;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.apachehttp.execute.statuscode");
		buildPost = Outside.service(this,"gus06.sys.apachehttp.build.request.post");
	}
	
	public Object t(Object obj) throws Exception
	{
		HttpPost post = (HttpPost) buildPost.t(obj);
		return execute.t(post);
	}
}
