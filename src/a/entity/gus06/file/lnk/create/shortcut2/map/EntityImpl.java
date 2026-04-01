package a.entity.gus06.file.lnk.create.shortcut2.map;

import java.io.File;
import java.util.HashMap;
import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170405";}
	
	public static final String KEY_TARGET = "target";
	public static final String KEY_LNK = "lnk";
	public static final String KEY_ICON = "icon";

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.lnk.create.shortcut2");
	}


	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		File target = (File) get(map,KEY_TARGET);
		File lnk = (File) get(map,KEY_LNK);
		Object icon = get(map,KEY_ICON);
		
		perform.p(new Object[]{target,lnk,icon});
	}
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
