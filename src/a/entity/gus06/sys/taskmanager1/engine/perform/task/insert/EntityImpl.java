package a.entity.gus06.sys.taskmanager1.engine.perform.task.insert;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250819";}

	public static final String TABLE_NAME = "task";

	public static final String COL_ID = "id";
	public static final String COL_TITLE = "title";
	public static final String COL_CONTENT = "content";
	public static final String COL_STATUS = "status";
	public static final String COL_LEVEL = "level";
	public static final String COL_PRIORITY = "priority";
	public static final String COL_DATE_CREATED = "date_created";

	private SimpleDateFormat sdf;
	
	public EntityImpl() throws Exception
	{
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];
		
		Object title = get(data, COL_TITLE);
		Object content = get(data, COL_CONTENT);
		Object status = get(data, COL_STATUS);
		Object level = get(data, COL_LEVEL);
		Object priority = get(data, COL_PRIORITY);
		Object dateCreated = sdf.format(new Date());

		String sql = "INSERT INTO " + TABLE_NAME + " (" 
		+ COL_TITLE + "," 
		+ COL_CONTENT + "," 
		+ COL_STATUS + ","
		+ COL_LEVEL + "," 
		+ COL_PRIORITY + "," 
		+ COL_DATE_CREATED + ") "
		+ "VALUES (?,?,?,?,?,?)";

		Long id = executeUpdate(cx, sql, title, content, status, level, priority, dateCreated);
		data.put(COL_ID, id);
	}

	private Long executeUpdate(Connection cx, String sql, Object... params) throws SQLException
	{
		try(PreparedStatement st = cx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
		{
			for (int i = 0; i < params.length; i++) st.setObject(i + 1, params[i]);
			st.executeUpdate();
			try (ResultSet rs = st.getGeneratedKeys())
			{return rs.next() ? rs.getLong(1) : null;}
		}
	}
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
}
