package a.entity.gus06.y.h2sql1.retrieve.tablesizemap;

import a.framework.*;
import java.util.HashMap;
import java.sql.Connection;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250729";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return new HashMap();
		G getCx = (G) obj;
		Connection cx = (Connection) getCx.g();
		
		Map map = new HashMap();
		
		String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA = 'PUBLIC'";
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		List names = new ArrayList();
		while(rs.next()) names.add(rs.getString(1));
		rs.close();
		st.close();
		
		
		for(int i=0;i<names.size();i++)
		{
			String name = (String) names.get(i);
			st = cx.prepareStatement("SELECT COUNT(*) FROM "+name);
			
			rs = st.executeQuery();
			rs.next();
			long count = rs.getLong(1);
			map.put(name, count);
			rs.close();
			st.close();
		}
		
		cx.close();
		return map;
	}
}