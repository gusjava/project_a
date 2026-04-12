package a.entity.gus.y.filedb1.cx.initdb.fk;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240125";}
	
	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		{
			String sql = "ALTER TABLE fileaction ADD FOREIGN KEY (md5) REFERENCES file(md5)";
			execute(cx, sql);
		}
		
		{
			String sql = "ALTER TABLE fileaction ADD FOREIGN KEY (root_id) REFERENCES root(id)";
			execute(cx, sql);
		}

		{
			String sql = "ALTER TABLE filelocation ADD FOREIGN KEY (md5) REFERENCES file(md5)";
			execute(cx, sql);
		}

		{
			String sql = "ALTER TABLE filename ADD FOREIGN KEY (md5) REFERENCES file(md5)";
			execute(cx, sql);
		}

		{
			String sql = "ALTER TABLE hddstate ADD FOREIGN KEY (serial) REFERENCES hdd(serial)";
			execute(cx, sql);
		}

		{
			String sql = "ALTER TABLE root ADD FOREIGN KEY (serial) REFERENCES hdd(serial)";
			execute(cx, sql);
		}

		{
			String sql = "ALTER TABLE scan ADD FOREIGN KEY (root_id) REFERENCES root(id)";
			execute(cx, sql);
		}

	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
