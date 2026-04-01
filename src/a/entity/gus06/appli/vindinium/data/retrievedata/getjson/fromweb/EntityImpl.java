package a.entity.gus06.appli.vindinium.data.retrievedata.getjson.fromweb;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl extends S1 implements Entity, T {

	public String creationDate() {return "20170923";}


	private Service sendPost;
	private Service recorder;

	public EntityImpl() throws Exception
	{
		sendPost = Outside.service(this,"*gus06.web.httprequest.post.send");
		recorder = Outside.service(this,"gus06.appli.vindinium.data.retrievedata.recorder");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		String url = (String) t[0];
		Map input = (Map) t[1];
		
		start();
		
		Map post = new HashMap();
		post.put("body",input);
		post.put("url",url);
		
		String text = (String) sendPost.t(post);
		recorder.p(text);
		end();
		
		return text;
	}
	
	
	private void start()
	{send(this,"start()");}
	
	private void end()
	{send(this,"end()");}
}
