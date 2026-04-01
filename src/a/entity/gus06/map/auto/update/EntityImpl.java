package a.entity.gus06.map.auto.update;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250826";}

	public static final String KEY_UPDATER = "updater";
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		if(!map.containsKey(KEY_UPDATER)) return map;
		T updater = (T) map.get(KEY_UPDATER);
		return updater.t(map);
	}
}
