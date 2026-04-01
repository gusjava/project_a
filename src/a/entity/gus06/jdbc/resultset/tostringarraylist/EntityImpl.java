package a.entity.gus06.jdbc.resultset.tostringarraylist;

import a.framework.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160228";}
	


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		
		ArrayList list = new ArrayList();
		while(rs.next())
		{
			String[] row = new String[count];
			for(int i=0;i<count;i++)
			row[i] = rs.getString(i+1);
			
			list.add(row);
		}
		
		rs.close();
		return list;
	}
}
