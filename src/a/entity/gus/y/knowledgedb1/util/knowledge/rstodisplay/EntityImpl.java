package a.entity.gus.y.knowledgedb1.util.knowledge.rstodisplay;

import java.sql.ResultSet;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260507";}

	public static final String COL_CODE = "code";
	public static final String COL_ACTION = "action";
	public static final String COL_OBJECT = "object";

	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		
		String code = rs.getString(COL_CODE);
		String action = rs.getString(COL_ACTION);
		String object = rs.getString(COL_OBJECT);
		
		return code+":"+action+":"+object;
	}
}
