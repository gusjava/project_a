package a.entity.gus06.sys.token1.store;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T, R, V, F {

	public String creationDate() {return "20160306";}
	
	public static final String TOKEN_TIMEOUT = "token_time_out";
	public static final String TOKEN_TIME_CREATION = "token_time_creation";
	public static final String TOKEN_TIME_EXPIRED = "token_time_expired";
	public static final String TOKEN_USENB = "token_usenb";
	public static final String TOKEN_USENB_MAX = "token_usenb_max";
	public static final String TOKEN_VALUE = "token_value";

	public static final long DEFAULT_TIMEOUT = 120000; //2MIN
	public static final int DEFAULT_USENB_MAX = 1;
	


	private Service buildAccess;
	private Service randomToken1;
	private Service emptyDir;
	
	private File storeDir;
	private Object access;
	
	private long defaultTimeout;
	private int defaultUseNbMax;




	public EntityImpl() throws Exception
	{
		buildAccess = Outside.service(this,"gus06.dir.accessbuilder.properties");
		randomToken1 = Outside.service(this,"gus06.data.generate.string.random.token1");
		emptyDir = Outside.service(this,"gus06.dir.op.empty");
		
		storeDir = (File) Outside.resource(this,"defaultdir");
		emptyDir.p(storeDir);
		
		access = buildAccess.t(storeDir);
		
		defaultTimeout = DEFAULT_TIMEOUT;
		defaultUseNbMax = DEFAULT_USENB_MAX;
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("timeout")) {defaultTimeout = toLong(obj);return;}
		if(key.equals("useNbMax")) {defaultUseNbMax = toInt(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key==null) return null;
		Map map = (Map) ((R) access).r(key);
		
		if(map==null) return null;
		if(!check(map,TOKEN_VALUE,key)) return null;
		if(isExpired(map)) return null;
		if(isOverUsed(map)) return null;
		
		increateNbUser(map);
		((V) access).v(key,map);
		
		return map;
	}
	
	public boolean f(Object obj) throws Exception
	{
		return r((String) obj)!=null;
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Object timeoutObj = get(map,TOKEN_TIMEOUT);
		long timeout = timeoutObj!=null ? toLong(timeoutObj) : defaultTimeout;
		
		Object useNbMaxObj = get(map,TOKEN_USENB_MAX);
		int useNbMax = useNbMaxObj!=null ? toInt(useNbMaxObj) : defaultUseNbMax;
		
		String token = (String) randomToken1.g();
		long timeCreation = System.currentTimeMillis();
		long timeExpired = timeCreation+timeout;
		
		map.put(TOKEN_VALUE,token);
		map.put(TOKEN_TIME_CREATION,""+timeCreation);
		map.put(TOKEN_TIME_EXPIRED,""+timeExpired);
		map.put(TOKEN_USENB_MAX,""+useNbMax);
		map.put(TOKEN_USENB,"0");
		
		((V) access).v(token,map);
		return token;
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private boolean check(Map map, String key, Object value)
	{
		if(!map.containsKey(key)) return false;
		return map.get(key).equals(value);
	}
	
	
	private boolean isExpired(Map map)
	{
		Object expired = get(map,TOKEN_TIME_EXPIRED);
		if(expired==null) return true;
		long t = toLong(expired);
		return t <= System.currentTimeMillis();
	}
	
	
	private boolean isOverUsed(Map map)
	{
		Object max = get(map,TOKEN_USENB_MAX);
		if(max==null) return false;
		
		Object nb = get(map,TOKEN_USENB);
		if(nb==null) return false;
		
		int max_ = toInt(max);
		int nb_ = toInt(nb);
		
		if(max_<0) return false;
		return nb_>=max_;
	}
	
	
	private void increateNbUser(Map map)
	{
		Object nbObj = get(map,TOKEN_USENB);
		int nb = nbObj==null ? 0 : toInt(nbObj);
		map.put(TOKEN_USENB,""+(nb+1));
	}


	private long toLong(Object obj)
	{return Long.parseLong(""+obj);}

	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
