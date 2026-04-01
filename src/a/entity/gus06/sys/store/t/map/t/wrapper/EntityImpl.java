package a.entity.gus06.sys.store.t.map.t.wrapper;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140929";}


	public static final String KEY_RESULT = "result";
	
	
	
	
	
	private Service findObj;
	
	public EntityImpl() throws Exception
	{
		findObj = Outside.service(this,"gus06.sys.store.obj.find");
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Object r = findObj(get(map,KEY_RESULT));
		return new T0(r);
	}
	
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	private Object findObj(String rule) throws Exception
	{return findObj.t(rule);}
	
	
	private class T0 implements T
	{
		private Object r;
		public T0(Object r){this.r = r;}
		public Object t(Object obj) throws Exception
		{return r;}
	}
}
