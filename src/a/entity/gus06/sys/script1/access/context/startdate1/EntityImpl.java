package a.entity.gus06.sys.script1.access.context.startdate1;

import a.framework.*;
import java.util.Map;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170329";}
	
	public static final String C_START_DATE = "start_date";

	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		Date date = (Date) get(context,C_START_DATE);
		if(date==null) throw new Exception("Start date not found inside context");
		return date;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
