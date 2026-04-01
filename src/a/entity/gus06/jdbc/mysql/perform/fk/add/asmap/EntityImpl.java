package a.entity.gus06.jdbc.mysql.perform.fk.add.asmap;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170321";}


	private Service buildSql;
	private Service executeSql;
	private Service protectedPath;

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.foreignkey.add");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		protectedPath = Outside.service(this,"gus06.jdbc.mysql.check.protectedpath");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Map map = (Map) o[2];
		
		String[] n = path.split("\\.");
		String tableName = n[n.length-1];
		
		String column1 = get1(map,"col1");
		String refTable = get1(map,"ref");
		String column2 = get(map,"col2","id");
		
		String fkName0 = "fk_"+tableName+"_"+column1;
		String fkName = get(map,"name",fkName0);
		
		if(protectedPath.f(path)) throw new Exception("Attempt to alter table: "+path);
		
		String sql = (String) buildSql.t(new Object[]{path,fkName,column1,refTable,column2});
		executeSql.p(new Object[]{cx,sql});
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