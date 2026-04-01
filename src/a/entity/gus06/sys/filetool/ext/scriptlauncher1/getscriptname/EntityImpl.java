package a.entity.gus06.sys.filetool.ext.scriptlauncher1.getscriptname;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160928";}
	
	public static final String KEY_SCRIPTNAME = "scriptname";
	public static final String DEFAULT_SCRIPTNAME = "script.gus";
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		if(map==null) throw new Exception("Map is null");
		
		return get(map,KEY_SCRIPTNAME,DEFAULT_SCRIPTNAME);
	}
	
	
	private Object get(Map map, String key, Object defaultValue) throws Exception
	{
		if(!map.containsKey(key)) return defaultValue;
		return map.get(key);
	}
}
