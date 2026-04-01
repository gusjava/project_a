package a.entity.gus06.sys.jpwce1.engine.cx.insert.results;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250721";}

	public static final String TABLE_NAME = "results";

	public static final String COL_ID = "id";
	public static final String COL_DATE = "date";
	public static final String COL_QUESTION = "question";
	public static final String COL_ANSWER_RIGHT = "answer_right";
	public static final String COL_ANSWER_USER = "answer_user";
	public static final String COL_SUCCESS = "success";
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Object date = data.get(COL_DATE);
		Object question = data.get(COL_QUESTION);
		Object answerRight = data.get(COL_ANSWER_RIGHT);
		Object answerUser = data.get(COL_ANSWER_USER);
		Object success = data.get(COL_SUCCESS);

		String sql = "INSERT INTO " + TABLE_NAME + " (" 
		+ COL_DATE + "," 
		+ COL_QUESTION + ","
		+ COL_ANSWER_RIGHT + ","
		+ COL_ANSWER_USER + ","
		+ COL_SUCCESS + ") VALUES (?,?,?,?,?)";

		executeUpdate(cx, sql, date, question, answerRight, answerUser, success);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}