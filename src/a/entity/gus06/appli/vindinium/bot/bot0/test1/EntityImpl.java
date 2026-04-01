package a.entity.gus06.appli.vindinium.bot.bot0.test1;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20170923";}

	public static final String BOTNAME = "gus-test1";
	public static final int LIFELIMIT = 30;
	public static final String STRATEGY = "t01";

	private Service directionForPath;
	private Service strategy;
	
	public EntityImpl() throws Exception
	{
		directionForPath = Outside.service(this,"gus06.appli.vindinium.bot.tool.directionforpath");
		strategy = Outside.service(this,"gus06.appli.vindinium.bot.strategy.path.t01");
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("botname")) return BOTNAME;
		if(key.equals("keys")) return new String[]{"botname"};
		throw new Exception("Unknown key: "+key);
	}


	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		
		data.put(DATA_BOT_._BOT_STRATEGYPARAMS,params());
		data.put(DATA_BOT_._BOT_STRATEGY,STRATEGY);
		
		int[][] path = choosePath(data);
		data.put(DATA_BOT_._BOT_PATH,path);
		
		return direction(path);
	}
	
	private Map params()
	{
		Map m = new HashMap();
		m.put("limit",""+LIFELIMIT);
		return m;
	}
	
	private int[][] choosePath(Map data) throws Exception
	{return (int[][]) strategy.t(data);}
	
	private String direction(int[][] path) throws Exception
	{return (String) directionForPath.t(path);}
}
