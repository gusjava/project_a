package a.entity.gus.y.knowledgedb1.knowledge_tag.renamebytag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260429";}

	public Object t(Object obj) throws Exception
	{
		Object[] args = (Object[]) obj;
		Connection cx = (Connection) args[0];
		String oldTag = (String) args[1];
		String newTag = (String) args[2];

		PreparedStatement st = cx.prepareStatement("UPDATE knowledge_tag SET tag=? WHERE tag=?");
		st.setString(1, newTag);
		st.setString(2, oldTag);
		st.executeUpdate();
		st.close();
		return null;
	}
}