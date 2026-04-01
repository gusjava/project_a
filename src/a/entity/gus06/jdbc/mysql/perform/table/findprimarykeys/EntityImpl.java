package a.entity.gus06.jdbc.mysql.perform.table.findprimarykeys;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160405";}

	public static final String FIELD = "COLUMN_NAME";



	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		Connection cx = (Connection) t[0];
		String path = (String) t[1];
		
		String[] n = path.split("\\.");
		
		String dbName = n.length==2?n[0]:null;
		String tableName = n.length==2?n[1]:n[0];
		
		ResultSet rs = cx.getMetaData().getPrimaryKeys(dbName,null,tableName);
		
		Set set = new HashSet();
		while(rs.next()) set.add(rs.getString(FIELD));
		rs.close();
		return set;
	}
}
