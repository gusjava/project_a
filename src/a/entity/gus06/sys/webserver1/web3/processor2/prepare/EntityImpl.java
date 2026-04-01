package a.entity.gus06.sys.webserver1.web3.processor2.prepare;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160219";}
	
	
	public static final String KEY_SESSION = "session";
	public static final String KEY_CLIENT = "client";
	public static final String KEY_SERVER = "server";
	public static final String KEY_REQUEST = "request";
	
	public static final String KEY_OUTPUT = "output";
	public static final String KEY_OUTPUT_HEADER = "output_header";
	
	



	private Service buildSession;
	private Service buildClient;
	private Service buildServer;
	private Service buildRequest;


	public EntityImpl() throws Exception
	{
		buildSession = Outside.service(this,"gus06.sys.webserver1.web3.processor2.prepare.session");
		buildClient = Outside.service(this,"gus06.sys.webserver1.web3.processor2.prepare.client");
		buildServer = Outside.service(this,"gus06.sys.webserver1.web3.processor2.prepare.server");
		buildRequest = Outside.service(this,"gus06.sys.webserver1.web3.processor2.prepare.request");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map input = (Map) obj;
		
		Map session = (Map) buildSession.t(input);
		Map client = (Map) buildClient.t(input);
		Map server = (Map) buildServer.g();
		Map request = (Map) buildRequest.t(input);
		
		
		
		Map map = new HashMap();
		
		map.put(KEY_SESSION,session);
		map.put(KEY_CLIENT,client);
		map.put(KEY_SERVER,server);
		map.put(KEY_REQUEST,request);
		
		map.put(KEY_OUTPUT,new StringBuffer());
		map.put(KEY_OUTPUT_HEADER,new HashMap());
		
		return map;
	}
}
