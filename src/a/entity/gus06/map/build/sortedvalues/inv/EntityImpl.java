package a.entity.gus06.map.build.sortedvalues.inv;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220628";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return build((Map) obj);
		throw new Exception("Invalid input data: "+obj.getClass().getName());
	}
	
	
	private List build(Map map)
	{
		List values = new ArrayList(map.values());
		Collections.sort(values, Collections.reverseOrder());
		return values;
	}
}