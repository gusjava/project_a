package a.entity.gus.y.server1.engine.cmd.r.tags;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service roadmapCx;

	private static final String[] TAG_TABLES = {"note_tag", "objective_tag", "task_tag"};

	public EntityImpl() throws Exception
	{
		roadmapCx = Outside.service(this, "gus.y.roadmapdb1.cx.main");
	}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) roadmapCx.g();
		Set tags = new TreeSet();
		for(int i = 0; i < TAG_TABLES.length; i++)
		{
			Statement st = cx.createStatement();
			ResultSet r = st.executeQuery("SELECT DISTINCT TAG FROM " + TAG_TABLES[i]);
			while(r.next()) tags.add(r.getString(1));
			r.close();
			st.close();
		}
		return new ArrayList(tags);
	}
}