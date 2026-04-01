package a.entity.gus06.sys.expression1.apply.op._jdialog_inputarea;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220602";}
	
	public final static String KEY_TITLE = "title";
	public final static String KEY_INITVALUE = "initvalue";
	
	
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.input.textarea.dialog");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String)	return input((String) obj);
		if(obj instanceof Map)		return input((Map) obj);
		if(obj instanceof String[])	return input((String[]) obj);
		if(obj instanceof List)		return input((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String input(String title) throws Exception
	{
		return (String) perform.t(title);
	}
	
	private String input(Map map) throws Exception
	{
		String title = get(map,KEY_TITLE,"");
		String initValue = get(map,KEY_INITVALUE,"");
		
		return (String) perform.t(new String[]{title, initValue});
	}
	
	private String input(List list) throws Exception
	{
		String title = get(list,0,"");
		String initValue = get(list,1,"");
		
		return (String) perform.t(new String[]{title, initValue});
	}
	
	private String input(String[] array) throws Exception
	{
		String title = get(array,1,"");
		String initValue = get(array,2,"");
		
		return (String) perform.t(new String[]{title, initValue});
	}
	
	
	
	
	
	private String get(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
	
	private String get(List list, int index, String defaultValue)
	{
		if(list.size()<=index) return defaultValue;
		return (String) list.get(index);
	}
	
	private String get(String[] array, int index, String defaultValue)
	{
		if(array.length<=index) return defaultValue;
		return array[index];
	}
}