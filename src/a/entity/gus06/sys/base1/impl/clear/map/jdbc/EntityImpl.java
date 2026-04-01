package a.entity.gus06.sys.base1.impl.clear.map.jdbc;

import a.framework.*;
import java.util.Map;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150807";}

	
	public static final String KEY_CX = "cx";
	public static final String KEY_PATH = "path";
	public static final String KEY_STRUCTURE = "structure";
	
	public static final String STRUCT_MAP1 = "map1";
	public static final String STRUCT_MAP2 = "map2";
	public static final String STRUCT_LINK = "link";
	public static final String STRUCT_BLOB = "blob";
	
	

	private Service clear;



	public EntityImpl() throws Exception
	{
		clear = Outside.service(this,"gus06.jdbc.mysql.perform.table.truncate");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Connection cx = (Connection) get(map,KEY_CX);
		String path = (String) get(map,KEY_PATH);
		String structure = (String) get(map,KEY_STRUCTURE);
		
		clear.p(new Object[]{cx,path});
	}
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
