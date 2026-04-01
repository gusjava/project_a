package a.entity.gus06.sys.store.t.map.t.string.string.beforeafter;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140909";}


	public static final String KEY_BEFORE = "before";
	public static final String KEY_AFTER = "after";
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String before = get(map,KEY_BEFORE);
		String after = get(map,KEY_AFTER);
		return new T0(before,after);
	}
	
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	
	private class T0 implements T
	{
		private String before;
		private String after;
		
		public T0(String before, String after)
		{
			this.before = before;
			this.after = after;
		}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			if(before!=null) s = before+s;
			if(after!=null) s = s+after;
			return s;
		}
	}
}
