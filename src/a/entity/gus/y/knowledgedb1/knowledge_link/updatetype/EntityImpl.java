package a.entity.gus.y.knowledgedb1.knowledge_link.updatetype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260429";}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 4)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idLinker = (Long) o[1];
		Long idLinked = (Long) o[2];
		String type = (String) o[3];

		String sql = "UPDATE knowledge_link SET type=? WHERE id_linker=? AND id_linked=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, type);
		st.setObject(2, idLinker);
		st.setObject(3, idLinked);
		st.executeUpdate();
		st.close();
		return null;
	}
}