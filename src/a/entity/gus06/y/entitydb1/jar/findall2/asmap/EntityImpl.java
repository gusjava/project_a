package a.entity.gus06.y.entitydb1.jar.findall2.asmap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251230";}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		Map data = new HashMap();
		{
			StringBuilder sql = new StringBuilder("SELECT");
			sql.append(" j.sha1 AS jar_sha1,");
			sql.append(" j.file_name AS jar_name,");
			sql.append(" j.maven_id AS maven_id,");
			sql.append(" COUNT(DISTINCT ei.entity_name) AS entity_count");
			sql.append(" FROM jar j");
			sql.append(" LEFT JOIN jar_class jc ON jc.jar_sha1 = j.sha1");
			sql.append(" LEFT JOIN entity_import ei ON (");
			sql.append("ei.entity_import_wildcard = 0 AND ei.entity_import = jc.jar_class)");
			sql.append(" GROUP BY j.sha1, j.maven_id");
			sql.append(" ORDER BY entity_count DESC");
			Statement st = cx.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			while (rs.next())
			{
				Map m = new HashMap();
				transfer(m, rs, "jar_sha1");
				transfer(m, rs, "jar_name");
				transfer(m, rs, "maven_id");
				transfer(m, rs, "entity_count");
	
				String jarName = (String) m.get("jar_name");
				data.put(jarName, m);
			}
			rs.close();
			st.close();
		}
		{
			StringBuilder sql = new StringBuilder("SELECT");
			sql.append(" j.sha1 AS jar_sha1,");
			sql.append(" j.file_name AS jar_name,");
			sql.append(" j.maven_id AS maven_id,");
			sql.append(" COUNT(DISTINCT ei.entity_name) AS entity_count");
			sql.append(" FROM jar j");
			sql.append(" LEFT JOIN jar_class jc ON jc.jar_sha1 = j.sha1");
			sql.append(" LEFT JOIN entity_import ei ON (");
			sql.append("ei.entity_import_wildcard = 1 AND ei.entity_import_package = jc.jar_class_package)");
			sql.append(" GROUP BY j.sha1, j.maven_id");
			sql.append(" ORDER BY entity_count DESC");
			Statement st = cx.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
	
			while (rs.next())
			{
				Map m = new HashMap();
				transfer(m, rs, "jar_sha1");
				transfer(m, rs, "jar_name");
				transfer(m, rs, "maven_id");
				transfer(m, rs, "entity_count");
	
				String jarName = (String) m.get("jar_name");
				Map old = (Map) data.get(jarName);
				if (old == null)
				{
					data.put(jarName, m);
				}
				else
				{
					Number c1 = (Number) old.get("entity_count");
					Number c2 = (Number) m.get("entity_count");
					old.put("entity_count", c1.intValue() + c2.intValue());
				}
			}
			rs.close();
			st.close();
		}
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}