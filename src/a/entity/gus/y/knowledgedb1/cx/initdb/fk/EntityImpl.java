package a.entity.gus.y.knowledgedb1.cx.initdb.fk;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		{ execute(cx, "ALTER TABLE knowledge_link ADD FOREIGN KEY (id_linker) REFERENCES knowledge(id)"); }
		{ execute(cx, "ALTER TABLE knowledge_link ADD FOREIGN KEY (id_linked) REFERENCES knowledge(id)"); }
		{ execute(cx, "ALTER TABLE knowledge_tag ADD FOREIGN KEY (id_knowledge) REFERENCES knowledge(id)"); }
		{ execute(cx, "ALTER TABLE knowledge_feedback ADD FOREIGN KEY (id_knowledge) REFERENCES knowledge(id)"); }
		{ execute(cx, "ALTER TABLE todo_link ADD FOREIGN KEY (id_linker) REFERENCES todo(id)"); }
		{ execute(cx, "ALTER TABLE todo_link ADD FOREIGN KEY (id_linked) REFERENCES todo(id)"); }
		{ execute(cx, "ALTER TABLE todo_knowledge ADD FOREIGN KEY (id_todo) REFERENCES todo(id)"); }
		{ execute(cx, "ALTER TABLE todo_knowledge ADD FOREIGN KEY (id_knowledge) REFERENCES knowledge(id)"); }
		{ execute(cx, "ALTER TABLE todo_tag ADD FOREIGN KEY (id_todo) REFERENCES todo(id)"); }
		{ execute(cx, "ALTER TABLE doc_x_tag ADD FOREIGN KEY (id) REFERENCES doc_x(id)"); }
		{ execute(cx, "ALTER TABLE doc_y_tag ADD FOREIGN KEY (id) REFERENCES doc_y(id)"); }
		{ execute(cx, "ALTER TABLE doc_z_tag ADD FOREIGN KEY (id) REFERENCES doc_z(id)"); }
		{ execute(cx, "ALTER TABLE spec_rule ADD FOREIGN KEY (spec_id) REFERENCES spec(id)"); }
		{ execute(cx, "ALTER TABLE spec_rule ADD FOREIGN KEY (rule_id) REFERENCES rule(id)"); }
		{ execute(cx, "ALTER TABLE doc_x_spec ADD FOREIGN KEY (doc_x_id) REFERENCES doc_x(id)"); }
		{ execute(cx, "ALTER TABLE doc_x_spec ADD FOREIGN KEY (spec_id) REFERENCES spec(id)"); }
		{ execute(cx, "ALTER TABLE doc_y_spec ADD FOREIGN KEY (doc_y_id) REFERENCES doc_y(id)"); }
		{ execute(cx, "ALTER TABLE doc_y_spec ADD FOREIGN KEY (spec_id) REFERENCES spec(id)"); }
		{ execute(cx, "ALTER TABLE doc_y_index ADD FOREIGN KEY (doc_y_id) REFERENCES doc_y(id)"); }
		{ execute(cx, "ALTER TABLE doc_y_member ADD FOREIGN KEY (doc_y_id) REFERENCES doc_y(id)"); }
		{ execute(cx, "ALTER TABLE doc_y_entry ADD FOREIGN KEY (doc_y_id) REFERENCES doc_y(id)"); }
		{ execute(cx, "ALTER TABLE doc_y_extension ADD FOREIGN KEY (unit_y_id) REFERENCES doc_y(id)"); }
		{ execute(cx, "ALTER TABLE doc_y_extension ADD FOREIGN KEY (spec_id) REFERENCES spec(id)"); }
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}