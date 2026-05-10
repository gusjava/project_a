package a.entity.gus.y.knowledgesys1.find.tags;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private static final String[] TAG_TABLES = {
		"knowledge_tag", "todo_tag", "doc_x_tag", "doc_y_tag", "doc_z_tag"
	};

	public EntityImpl() throws Exception {}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		Set tags = new TreeSet();
		for(int i = 0; i < TAG_TABLES.length; i++)
		{
			Statement st = cx.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT TAG FROM " + TAG_TABLES[i]);
			while(rs.next()) tags.add(rs.getString(1));
			rs.close();
			st.close();
		}
		return new ArrayList(tags);
	}
}