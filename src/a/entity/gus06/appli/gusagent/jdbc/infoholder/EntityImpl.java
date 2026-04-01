package a.entity.gus06.appli.gusagent.jdbc.infoholder;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150626";}
	
	public static final String KEY_HOST = "jdbc.host";
	public static final String KEY_PORT = "jdbc.port";
	public static final String KEY_USER = "jdbc.user";
	public static final String KEY_PWD = "jdbc.pwd";
	public static final String KEY_DBNAME = "jdbc.dbname";
	
	
	
	private Service pbe1Prop;
	
	private String[] info;


	public EntityImpl() throws Exception
	{
		pbe1Prop = Outside.service(this,"gus06.crypto.pbe1.prop.holder");
	}
	
	
	public Object g() throws Exception
	{
		if(info==null) init();
		return info;
	}
	
	
	private void init() throws Exception
	{
		Map prop = (Map) pbe1Prop.g();
		
		String host = get(prop,KEY_HOST);
		String port = get(prop,KEY_PORT);
		String user = get(prop,KEY_USER);
		String pwd = get(prop,KEY_PWD);
		String dbName = get(prop,KEY_DBNAME);
		
		String url = "jdbc:mysql://"+host+":"+port+"/"+dbName;
		
		info = new String[]{url,user,pwd};
	}
	
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
}
