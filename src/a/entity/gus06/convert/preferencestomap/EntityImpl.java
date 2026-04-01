package a.entity.gus06.convert.preferencestomap;

import a.framework.*;
import java.util.HashMap;
import java.util.prefs.Preferences;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180425";}


	
	public Object t(Object obj) throws Exception
	{
		Preferences prefs = (Preferences) obj;
		HashMap map = new HashMap();
		
		String[] keys = prefs.keys();
		for(String key : keys)
		{
			Object value = prefs.get(key,null);
			map.put(key,value);
		}
		return map;
	}
}
