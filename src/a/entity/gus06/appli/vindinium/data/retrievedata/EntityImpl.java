package a.entity.gus06.appli.vindinium.data.retrievedata;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, G, T, V {

	public String creationDate() {return "20170923";}
	
	private Service findInitUrl;
	private Service getJson;
	private Service isFinished;
	private Service formatData;
	
	private PrintStream out;
	
	private String mode;
	private String turns;
	private String key0;
	private String map;
	
	private String playUrl;
	private String initUrl;
	
	
	public EntityImpl() throws Exception
	{
		findInitUrl = Outside.service(this,"gus06.appli.vindinium.data.retrievedata.initurl");
		getJson = Outside.service(this,"gus06.appli.vindinium.data.retrievedata.getjson");
		isFinished = Outside.service(this,"gus06.appli.vindinium.data.game.isfinished");
		formatData = Outside.service(this,"gus06.appli.vindinium.data.retrievedata.format");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}

	public void v(String key, Object obj) throws Exception
	{
		if(key.equals(PARAMS.MODE)) {mode = (String) obj;return;}
		if(key.equals(PARAMS.TURNS)) {turns = (String) obj;return;}
		if(key.equals(PARAMS.KEY)) {key0 = (String) obj;return;}
		if(key.equals(PARAMS.MAP)) {map = (String) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}

	
	public Object g() throws Exception
	{
		playUrl = null;
		initUrl = initUrl();
		
		Map params = new HashMap();
		put(params,PARAMS.TURNS,turns);
		put(params,PARAMS.KEY,key0);
		put(params,PARAMS.MAP,map);
		
		out.println("Waiting for game start at url: "+initUrl);
		Map data = retrieveData(initUrl,params);
		out.println("Game has just started !!");
		
		return data;
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String dir = (String) obj;
		if(playUrl==null) throw new Exception("Game has not been initialized yet");
		
		Map params = new HashMap();
		put(params,PARAMS.TURNS,turns);
		put(params,PARAMS.KEY,key0);
		put(params,PARAMS.MAP,map);
		put(params,PARAMS.DIR,dir);
		
		return retrieveData(playUrl,params);
	}

	
	private Map retrieveData(String url, Map params) throws Exception
	{
		Map data = (Map) getJson.t(new Object[]{url,params});
		playUrl = findPlayUrl(data);
		
		formatData.p(data);
		return data; 
	}
	
	private String findPlayUrl(Map data) throws Exception
	{
		if(finished(data)) return null;
		return (String) data.get(DATA.K_PLAYURL);
	}
	
	private boolean finished(Map data) throws Exception
	{return isFinished.f(data);}
	
	private String initUrl() throws Exception
	{return (String) findInitUrl.t(mode);}
	
	private void put(Map map, String key, String value)
	{if(value!=null) map.put(key,value);}
}
