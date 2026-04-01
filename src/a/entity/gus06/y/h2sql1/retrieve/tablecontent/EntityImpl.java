package a.entity.gus06.y.h2sql1.retrieve.tablecontent;

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
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return new ArrayList();
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		G getCx = (G) o[0];
		String tableName = (String) o[1];
		List columns = (List) o[2];
		
		Connection cx = (Connection) getCx.g();
		
		String sql = "SELECT * FROM "+tableName+" LIMIT 1000";
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		int columnNb = columns.size();
		List list = new ArrayList();
		while (rs.next())
		{
			Object[] row = new Object[columnNb];
			for(int i=0;i<columnNb;i++)
			{
				String col = (String) columns.get(i);
				row[i] = rs.getObject(col);
			}
			list.add(row);
		}
		rs.close();
		st.close();
		cx.close();
		
		return list;
	}
}