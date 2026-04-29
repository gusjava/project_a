package a.entity.gus.y.knowledgedb1.knowledge_link.delete3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260429";}

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idLinker = (Long) o[1];
		Long idLinked = (Long) o[2];

		String sql = "DELETE FROM knowledge_link WHERE id_linker=? AND id_linked=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, idLinker);
		st.setObject(2, idLinked);
		st.executeUpdate();
		st.close();
	}
}