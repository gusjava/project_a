package a.entity.gus06.jdbc.resultset.get.colarray;

import a.framework.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151115";}
	


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		
		String[] col = new String[count];
		for(int i=0;i<count;i++)
		col[i] = rsmd.getColumnName(i+1);
		
		return col;
	}
}
