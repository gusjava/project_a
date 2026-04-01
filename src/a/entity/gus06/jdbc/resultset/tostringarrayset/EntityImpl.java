package a.entity.gus06.jdbc.resultset.tostringarrayset;

import a.framework.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190516";}
	


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		
		HashSet set = new HashSet();
		while(rs.next())
		{
			String[] row = new String[count];
			for(int i=0;i<count;i++)
			row[i] = rs.getString(i+1);
			
			set.add(row);
		}
		
		rs.close();
		return set;
	}
}
