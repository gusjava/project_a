package a.entity.gus06.sys.learning1.engine.perform.description;

import a.framework.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250713";}

	public static final String STATUS_EMPTY = "EMPTY";
	public static final String STATUS_RECENT = "RECENT";
	public static final String STATUS_UNCERTAIN = "UNCERTAIN";
	public static final String STATUS_SURE = "SURE";
	public static final String STATUS_OVER = "OVER";
	
	
	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		String corpus = buildCorpus(cx);
		int successNb = buildSuccessNb(cx);
		int failNb = buildFailNb(cx);
		cx.close();
		return "["+corpus+"] +"+successNb+" -"+failNb;
	}
	
	public String buildCorpus(Connection cx) throws Exception
	{
		String sql = "SELECT status FROM questions";
		
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		int emptyNb = 0;
		int recentNb = 0;
		int uncertainNb = 0;
		int sureNb = 0;
		int overNb = 0;
		int unknownNb = 0;
		
		while(rs.next())
		{
			String status = rs.getString(1);
			switch(status)
			{
				case STATUS_EMPTY: emptyNb++;break;
				case STATUS_RECENT: recentNb++;break;
				case STATUS_UNCERTAIN: uncertainNb++;break;
				case STATUS_SURE: sureNb++;break;
				case STATUS_OVER: overNb++;break;
				default: unknownNb++;break;
			}
		}
		st.close();
		return emptyNb+" "+recentNb+" "+uncertainNb+" "+sureNb+" "+overNb+" "+unknownNb;
	}
	
	public int buildSuccessNb(Connection cx) throws Exception
	{
		String sql = "SELECT count(*) FROM results WHERE success=true";
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		Integer count = rs.next() ? (Integer) rs.getInt(1) : null;
		st.close();
		return count;
	}
	
	public int buildFailNb(Connection cx) throws Exception
	{
		String sql = "SELECT count(*) FROM results WHERE success=false";
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		Integer count = rs.next() ? (Integer) rs.getInt(1) : null;
		st.close();
		return count;
	}
}