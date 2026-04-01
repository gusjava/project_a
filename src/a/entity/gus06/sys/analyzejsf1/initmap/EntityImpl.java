package a.entity.gus06.sys.analyzejsf1.initmap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200331";}

	public static final String KEY0_CONF = "conf";
	public static final String KEY1_ROOT = "root";

	
	
	public Object t(Object obj) throws Exception
	{
		Map map = new HashMap();
		map.put(KEY0_CONF,buildConf(obj));
		return map;
	}
	
	
	private Map buildConf(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Invalid value: null");
		
		if(obj instanceof Map) return (Map) obj;
		if(obj instanceof File) return handleFile((File) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Map handleFile(File root) throws Exception
	{
		Map conf = new HashMap();
		conf.put(KEY1_ROOT,root);
		return conf;
	}
}
