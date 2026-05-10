package a.entity.gus.y.knowledgedb1.util.knowledge.rstomap1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260418";}

	public static final String COL_ID = "id";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_DATE_UPDATED = "date_updated";
	public static final String COL_CODE = "code";
	public static final String COL_ACTION = "action";
	public static final String COL_OBJECT = "object";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATE = "state";
	public static final String COL_PREPROCESSOR = "preprocessor";

	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;

		Map data = new HashMap();
		transfer(data, rs, COL_ID);
		transfer(data, rs, COL_DATE_CREATED);
		transfer(data, rs, COL_DATE_UPDATED);
		transfer(data, rs, COL_CODE);
		transfer(data, rs, COL_ACTION);
		transfer(data, rs, COL_OBJECT);
		transfer(data, rs, COL_DESCRIPTION);
		transfer(data, rs, COL_STATE);
		transfer(data, rs, COL_PREPROCESSOR);
		
		String display = 
			data.get(COL_CODE)+":"+ 
			data.get(COL_ACTION)+":"+ 
			data.get(COL_OBJECT);
		
		data.put("display", display);
		

		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}
