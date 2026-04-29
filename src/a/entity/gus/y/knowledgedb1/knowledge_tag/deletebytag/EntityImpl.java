package a.entity.gus.y.knowledgedb1.knowledge_tag.deletebytag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260429";}

	public Object t(Object obj) throws Exception
	{
		Object[] args = (Object[]) obj;
		Connection cx = (Connection) args[0];
		String tag = (String) args[1];

		PreparedStatement st = cx.prepareStatement("DELETE FROM knowledge_tag WHERE tag=?");
		st.setString(1, tag);
		st.executeUpdate();
		st.close();
		return null;
	}
}