package a.entity.gus.y.knowledgedb1.util.todo.rstomap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260423";}

	public static final String COL_ID = "id";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_CODE = "code";
	public static final String COL_TITLE = "title";
	public static final String COL_DESCRIPTION = "description";

	public Object t(Object obj) throws Exception {
		ResultSet rs = (ResultSet) obj;

		Map data = new HashMap();
		transfer(data, rs, COL_ID);
		transfer(data, rs, COL_DATE_CREATED);
		transfer(data, rs, COL_CODE);
		transfer(data, rs, COL_TITLE);
		transfer(data, rs, COL_DESCRIPTION);
		
		String display = 
			data.get(COL_CODE)+":"+ 
			data.get(COL_TITLE);
		
		data.put("display", display);
		

		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}
