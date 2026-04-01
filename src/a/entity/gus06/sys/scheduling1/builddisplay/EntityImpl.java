package a.entity.gus06.sys.scheduling1.builddisplay;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180120";}
	
	
	public static final String KEY_MONTHS = "months";
	public static final String KEY_MONTH_DAYS = "month_days";
	public static final String KEY_WEEK_DAYS = "week_days";
	public static final String KEY_HOURS = "hours";
	public static final String KEY_MINUTES = "minutes";
	public static final String KEY_SECONDES = "secondes";
	public static final String KEY_DATES = "dates";
	public static final String KEY_TIMES = "times";
	public static final String KEY_DURATION_MAX = "duration_max";
	public static final String KEY_DURATION_MIN = "duration_min";
	public static final String KEY_DATE_START = "date_start";
	public static final String KEY_DATE_END = "date_end";
	public static final String KEY_EACH = "each";
	public static final String KEY_LAST_DATE = "last_date";
	public static final String KEY_DEFAULT = "default";

	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		if(has(map,KEY_EACH))
		{
			String v = (String) get(map,KEY_EACH);
			return "each "+v;
		}
		
		if(has(map,KEY_DURATION_MAX))
		{
			String v = (String) get(map,KEY_DURATION_MAX);
			return "after "+v;
		}
		
		return "";
	}
	
	
	private boolean has(Map map, String key)
	{return map.containsKey(key);}
	
	private Object get(Map map, String key)
	{return has(map,key) ? map.get(key) : null;}
}
