package a.entity.gus.y.roadmapdb1.cx.initdb.fk;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260411";}

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		{ execute(cx, "ALTER TABLE objective_tag ADD FOREIGN KEY (id_objective) REFERENCES objective(id)"); }
		{ execute(cx, "ALTER TABLE task ADD FOREIGN KEY (id_objective) REFERENCES objective(id)"); }
		{ execute(cx, "ALTER TABLE task_tag ADD FOREIGN KEY (id_task) REFERENCES task(id)"); }
		{ execute(cx, "ALTER TABLE sprint_entry ADD FOREIGN KEY (id_sprint) REFERENCES sprint(id)"); }
		{ execute(cx, "ALTER TABLE sprint_entry ADD FOREIGN KEY (id_task) REFERENCES task(id)"); }
		{ execute(cx, "ALTER TABLE note_tag ADD FOREIGN KEY (id_note) REFERENCES note(id)"); }
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
