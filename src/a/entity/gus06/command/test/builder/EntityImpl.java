package a.entity.gus06.command.test.builder;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140725";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return build((Map) o[0],(String) o[1]);
	}

	private Object build(Map map, String rule) throws Exception
	{
		if(rule.equals("null")) return null;
		if(rule.equals("map")) return map;
		if(rule.equals("keys")) return map.keySet();

		String[] n = rule.split("#",2);
		if(n.length!=2) throw new Exception("Invalid rule: "+rule);

		String type = n[0];
		String info = n[1];

		if(type.equals("string")) return info;
		if(type.equals("outside")) return Outside.resource(this,info);
		if(type.equals("array")) return array(map,info);
		if(type.equals("concat")) return concat(map,info);
		if(type.equals("get")) return get(map,info);
		if(type.equals("g")) return g_(map,info);
		if(type.equals("i")) return i_(map,info);
		if(type.equals("t")) return t_(map,info);

		throw new Exception("Unknown type: "+type);
	}



	private Object array(Map map, String rule) throws Exception
	{
		String[] n = rule.split(" ");
		Object[] array = new Object[n.length];
		for(int i=0;i<n.length;i++) array[i] = get(map,n[i]);
		return array;
	}


	private Object concat(Map map, String rule) throws Exception
	{
		String[] n = rule.split(" ");
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++) b.append(get(map,n[i]).toString());
		return b.toString();
	}


	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}



	private Object g_(Map map, String rule) throws Exception
	{
		Object obj = get(map,rule);
		if(obj instanceof G) return ((G) obj).g();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}

	private Object i_(Map map, String rule) throws Exception
	{
		Object obj = get(map,rule);
		if(obj instanceof I) return ((I) obj).i();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}

	private Object t_(Map map, String rule) throws Exception
	{
		String[] n = rule.split(" ");
		if(n.length!=2) throw new Exception("Invalid rule: "+rule);

		Object obj1 = get(map,n[0]);
		Object obj2 = get(map,n[1]);

		if(obj1 instanceof T) return ((T) obj1).t(obj2);
		throw new Exception("Invalid data type: "+obj1.getClass().getName());
	}
}
