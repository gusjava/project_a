package a.entity.gus06.sys.taskmanager1.engine.perform.task.update;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250820";}

	public static final String TABLE_NAME = "task";

	public static final String COL_ID = "id";
	public static final String COL_TITLE = "title";
	public static final String COL_CONTENT = "content";
	public static final String COL_STATUS = "status";
	public static final String COL_LEVEL = "level";
	public static final String COL_PRIORITY = "priority";
	public static final String COL_DATE_UPDATED = "date_created";


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
		
		Object id = get(data, COL_ID);
		if(id==null) throw new Exception("Id not found for update");
		
		Object title = get(data, COL_TITLE);
		Object content = get(data, COL_CONTENT);
		Object status = get(data, COL_STATUS);
		Object level = get(data, COL_LEVEL);
		Object priority = get(data, COL_PRIORITY);
		Object dateUpdated = sdf.format(new Date());

		String sql = "UPDATE " + TABLE_NAME + " SET " 
		+ COL_TITLE + "=?, " 
		+ COL_CONTENT + "=?," 
		+ COL_STATUS + "=?,"
		+ COL_LEVEL + "=?," 
		+ COL_PRIORITY + "=?," 
		+ COL_DATE_UPDATED + "=? WHERE id=?";

		executeUpdate(cx, sql, title, content, status, level, priority, dateUpdated, id);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
}
