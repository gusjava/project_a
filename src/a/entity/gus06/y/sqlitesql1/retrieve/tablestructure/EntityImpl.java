package a.entity.gus06.y.sqlitesql1.retrieve.tablestructure;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250726";}
	
	public static final String KEY_CID = "cid";
	public static final String KEY_NAME = "name";
	public static final String KEY_TYPE = "type";
	public static final String KEY_NOTNULL = "notnull";
	public static final String KEY_DFLT_VALUE = "dflt_value";
	public static final String KEY_PK = "pk";
	
	
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
		
		Map map = new HashMap();
		while (rs.next())
		{
			Map m = new HashMap();
			transfer(m, rs, KEY_CID);
			transfer(m, rs, KEY_NAME);
			transfer(m, rs, KEY_TYPE);
			transfer(m, rs, KEY_NOTNULL);
			transfer(m, rs, KEY_DFLT_VALUE);
			transfer(m, rs, KEY_PK);
			
			map.put(m.get(KEY_NAME), m);
		}
		rs.close();
		st.close();
		cx.close();
		return map;
	}
	
	private void transfer(Map m, ResultSet rs, String key) throws Exception
	{m.put(key, rs.getObject(key));}
}
