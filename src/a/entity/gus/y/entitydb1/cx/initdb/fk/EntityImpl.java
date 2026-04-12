package a.entity.gus.y.entitydb1.cx.initdb.fk;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20231206";}
	
	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		{
			String sql = "ALTER TABLE entity_src_save ADD FOREIGN KEY (entity_name) REFERENCES entity(entity_name)";
			execute(cx, sql);
		}
		
		{
			String sql = "ALTER TABLE entity_service ADD FOREIGN KEY (entity_name) REFERENCES entity(entity_name)";
			execute(cx, sql);
		}

		{
			String sql = "ALTER TABLE entity_resource ADD FOREIGN KEY (entity_name) REFERENCES entity(entity_name)";
			execute(cx, sql);
		}

		{
			String sql = "ALTER TABLE entity_link ADD FOREIGN KEY (entity_name) REFERENCES entity(entity_name)";
			execute(cx, sql);
		}

		{
			String sql = "ALTER TABLE entity_link ADD FOREIGN KEY (link) REFERENCES entity(entity_name)";
			execute(cx, sql);
		}

		{
			String sql = "ALTER TABLE entity_missing_link ADD FOREIGN KEY (entity_name) REFERENCES entity(entity_name)";
			execute(cx, sql);
		}

		{
			String sql = "ALTER TABLE entity_compile_err ADD FOREIGN KEY (entity_name) REFERENCES entity(entity_name)";
			execute(cx, sql);
		}

		{
			String sql = "ALTER TABLE entity_xyz_err ADD FOREIGN KEY (entity_name) REFERENCES entity(entity_name)";
			execute(cx, sql);
		}
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
