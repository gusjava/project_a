package a.entity.gus06.sys.expression1.apply.op._sql1_unique_add;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220601";}


	private Service perform;
	private Service findArray;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.sql.constraint.unique.add");
		findArray = Outside.service(this,"gus06.find.objectarray");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Map) return perform.t(mapToArray((Map) obj));
		if(obj instanceof List) return perform.t(findArray.t(obj));
		if(obj instanceof Object[]) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object[] mapToArray(Map map) throws Exception
	{
		String path = get1(map,"path");
		String column = get1(map,"col");
		
		String uName0 = "u_"+path+"_"+column;
		String uName = get(map,"name",uName0);
		
		return new Object[]{path,uName,column};
	}
	
	private String get(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
	
	private String get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return (String) map.get(key);
	}
}