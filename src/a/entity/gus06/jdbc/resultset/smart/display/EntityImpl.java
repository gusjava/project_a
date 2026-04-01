package a.entity.gus06.jdbc.resultset.smart.display;

import a.framework.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231101";}
	

	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<count;i++)
		{
			String col = rsmd.getColumnName(i+1);
			b.append(col);
			if(i<count-1) b.append("\t");
		}
		b.append("\n");
		
		while(rs.next())
		{
			for(int i=0;i<count;i++)
			{
				Object value = rs.getObject(i+1);
				b.append(""+value);
				if(i<count-1) b.append("\t");
			}
			b.append("\n");
		}
		rs.close();
		return b.toString();
	}
}