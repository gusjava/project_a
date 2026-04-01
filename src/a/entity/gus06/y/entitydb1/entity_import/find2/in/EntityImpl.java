package a.entity.gus06.y.entitydb1.entity_import.find2.in;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251216";}

	public static final String TABLE_NAME = "entity_import";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_ENTITY_IMPORT = "entity_import";

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Set imports = (Set) o[1];
		
		if(imports==null) return new HashSet();
		if(imports.isEmpty()) return new HashSet();

		StringBuffer b = new StringBuffer();
		b.append("SELECT ");
		b.append(COL_ENTITY_NAME);
		b.append(" FROM ");
		b.append(TABLE_NAME);
		b.append(" WHERE ");
		b.append(COL_ENTITY_IMPORT);
		b.append(" IN (");

		int nb = imports.size();
		for (int i = 0; i < nb; i++)
		{
			b.append("?");
			if (i < nb - 1) b.append(",");
		}
		b.append(")");
		
		String sql = b.toString();
		List importList = new ArrayList(imports);
		
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < nb; i++) 
		st.setObject(i + 1, importList.get(i));
		ResultSet rs = st.executeQuery();

		Set data = new HashSet();
		while (rs.next())
		{
			String entityName = (String) rs.getObject(COL_ENTITY_NAME);
			data.add(entityName);
		}
		st.close();
		return data;
	}
}
