package a.entity.gus06.system.build.infomap;

import a.framework.*;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150626";}
	

	public static final String KEY_TIME_ZONE = "time_zone";
	public static final String KEY_USER_NAME = "user_name";
	public static final String KEY_COMPUTER_OS = "computer_os";
	public static final String KEY_COMPUTER_NAME = "computer_name";
	public static final String KEY_JAVA_VERSION = "java_version";


	private HashMap map;
	
	
	public Object g() throws Exception
	{
		if(map==null) init();
		return map;
	}
	
	
	
	private void init()
	{
		map = new HashMap();
		map.put(KEY_TIME_ZONE,time_zone());
		map.put(KEY_USER_NAME,user_name());
		map.put(KEY_COMPUTER_OS,computer_os());
		map.put(KEY_COMPUTER_NAME,computer_name());
		map.put(KEY_JAVA_VERSION,java_version());
	}
	
	
	
	private String time_zone()
	{return p("user.timezone");}
	
	private String user_name()
	{return p("user.name");}
	
	private String computer_os()
	{return p("os.name")+"_"+p("os.version")+"_"+p("os.arch")+"_"+p("user.language");}
	
	private String computer_name()
	{return e("COMPUTERNAME");}
	
	private String java_version()
	{return p("java.runtime.version")+"["+p("sun.arch.data.model")+"]";}
	
	
	
	
	
	
	private String p(String key)
	{return System.getProperty(key);}
	
	private String e(String key)
	{return System.getenv(key);}
}
