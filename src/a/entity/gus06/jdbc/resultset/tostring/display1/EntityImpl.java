package a.entity.gus06.jdbc.resultset.tostring.display1;

import a.framework.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190516";}
	


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
		b.append("\n\n");
		
		while(rs.next())
		{
			for(int i=0;i<count;i++)
			{
				Object value = rs.getObject(i+1);
				b.append(format(value));
				if(i<count-1) b.append("\t");
			}
			b.append("\n");
		}
		
		rs.close();
		return b.toString();
	}
	
	
	
	
	private String format(Object value)
	{
		if(value==null) return "null";
		if(value instanceof String) return formatString((String) value);
		return ""+value;
	}
	
	private String formatString(String s)
	{
		String s1 = s.split("\n")[0];
		if(s1.length()>20) s1 = s1.substring(0,20);
		if(s.equals(s1)) return s;
		return s1+"...";
	}
}
