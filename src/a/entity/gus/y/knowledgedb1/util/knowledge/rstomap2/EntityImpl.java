package a.entity.gus.y.knowledgedb1.util.knowledge.rstomap2;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260507";}

	public static final String COL_CODE = "code";
	public static final String COL_ACTION = "action";
	public static final String COL_OBJECT = "object";
	public static final String COL_DESCRIPTION = "description";

	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		
		String code = rs.getString(COL_CODE);
		String action = rs.getString(COL_ACTION);
		String object = rs.getString(COL_OBJECT);
		String description = rs.getString(COL_DESCRIPTION);
		String display = code+":"+action+":"+object;

		Map data = new HashMap();
		data.put("description", description);
		data.put("display", display);
		
		return data;
	}
}
