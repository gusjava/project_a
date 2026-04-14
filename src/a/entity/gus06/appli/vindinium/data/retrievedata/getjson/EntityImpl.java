package a.entity.gus06.appli.vindinium.data.retrievedata.getjson;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	private Service getResponse_fromLocal;
	private Service getResponse_fromWeb;
	private Service parseJson;
	
	public EntityImpl() throws Exception
	{
		getResponse_fromLocal = Outside.service(this,"gus06.appli.vindinium.engine.getresponse");
		getResponse_fromWeb = Outside.service(this,"gus06.appli.vindinium.data.retrievedata.getjson.fromweb");
		parseJson = Outside.service(this,"gus.x.json.parse1");
	}


	public Object t(Object obj) throws Exception
	{
		String text = getResponse(obj);
		return parseJson.t(text);
	}
	
	
	private String getResponse(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		String url = (String) t[0];
		
		if(url.startsWith("http://"))
			return (String) getResponse_fromWeb.t(obj);
		return (String) getResponse_fromLocal.t(obj);
	}
}
