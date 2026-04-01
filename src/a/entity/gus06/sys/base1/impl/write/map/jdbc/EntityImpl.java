package a.entity.gus06.sys.base1.impl.write.map.jdbc;

import a.framework.*;
import java.util.Map;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150803";}

	
	public static final String KEY_CX = "cx";
	public static final String KEY_PATH = "path";
	public static final String KEY_STRUCTURE = "structure";
	
	public static final String STRUCT_MAP1 = "map1";
	public static final String STRUCT_MAP2 = "map2";
	public static final String STRUCT_LINK = "link";
	public static final String STRUCT_BLOB = "blob";
	
	

	private Service writeMap1;
	private Service writeMap2;



	public EntityImpl() throws Exception
	{
		writeMap1 = Outside.service(this,"gus06.sys.jdbcmap1.perform.entry.insertupdate.id");
		writeMap2 = Outside.service(this,"gus06.sys.jdbcmap2.perform.entry.insertupdate.id");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		String id = (String) o[1];
		Object data = o[2];
		
		Connection cx = (Connection) get(map,KEY_CX);
		String path = (String) get(map,KEY_PATH);
		String structure = (String) get(map,KEY_STRUCTURE);
		
		if(structure.equals(STRUCT_MAP1))	{writeMap1.p(new Object[]{cx,path,id,data});return;}
		if(structure.equals(STRUCT_MAP2))	{writeMap2.p(new Object[]{cx,path,id,data});return;}
		
		throw new Exception("Unsupported structure: "+structure);
		
	}
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
