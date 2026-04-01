package a.entity.gus06.jdbc.gui.sqlquery1.rstostring;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150623";}



	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		int rowCount = 0;
		
		StringBuffer b1 = new StringBuffer();
		if(columnCount>0)
		{
			for(int i=0;i<columnCount;i++) b1.append(rsmd.getColumnName(i+1)+"\t");
			b1.append("\n");
			for(int i=0;i<columnCount;i++) b1.append(rsmd.getColumnTypeName(i+1)+"\t");
			b1.append("\n\n");
			
			while(rs.next())
			{
				rowCount++;
				for(int i=0;i<columnCount;i++) b1.append(getString(rs,i+1)+"\t");
				b1.append("\n");
			}
		}
		
		StringBuffer b2 = new StringBuffer();
		b2.append("toString: "+rs.toString()+"\n");
		b2.append("resultset type: "+typeToString(rs)+"\n");
		b2.append("column count: "+columnCount+"\n");
		b2.append("row count: "+rowCount);
		
		return b2.toString()+"\n\n"+b1.toString();
	}
	
	
	
	private String getString(ResultSet rs, int col) throws Exception
	{return rs.getString(col);}
	
	
	
	
	private String typeToString(ResultSet rs) throws Exception
	{
		int type = rs.getType();
		if(type==ResultSet.TYPE_FORWARD_ONLY)
			return "TYPE_FORWARD_ONLY";
		if(type==ResultSet.TYPE_SCROLL_INSENSITIVE)
			return "TYPE_SCROLL_INSENSITIVE";
		if(type==ResultSet.TYPE_SCROLL_SENSITIVE)
			return "TYPE_SCROLL_SENSITIVE";
		return "<UNKNOWN TYPE: "+type+">";
	}


}
