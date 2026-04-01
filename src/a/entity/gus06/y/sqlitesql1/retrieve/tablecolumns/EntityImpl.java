package a.entity.gus06.y.sqlitesql1.retrieve.tablecolumns;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250729";}
	
	public static final String KEY_NAME = "name";
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return new HashMap();
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G getCx = (G) o[0];
		String tableName = (String) o[1];
		
		Connection cx = (Connection) getCx.g();
		
		String sql = "PRAGMA table_info("+tableName+")";
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		List columns = new ArrayList();
		while (rs.next()) {columns.add(rs.getString(KEY_NAME));}
		
		rs.close();
		st.close();
		cx.close();
		return columns;
	}
}