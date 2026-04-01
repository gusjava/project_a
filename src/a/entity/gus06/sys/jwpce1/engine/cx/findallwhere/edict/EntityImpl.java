package a.entity.gus06.sys.jwpce1.engine.cx.findallwhere.edict;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.ResultSet;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250724";}

	public static final String TABLE_NAME = "edict";

	public static final String COL_ROMAJI = "romaji";
	public static final String COL_KANA = "kana";
	public static final String COL_KANJI = "kanji";
	public static final String COL_GRAMMATICAL_TAGS = "grammatical_tags";
	public static final String COL_SENSES = "senses";
	public static final String COL_COMMON = "common";
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String where = (String) o[1];

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + where;
		
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		List list = new ArrayList();
		while(rs.next())
		{
			Map m = new HashMap();
			
			transfer(m, rs, COL_ROMAJI);
			transfer(m, rs, COL_KANA);
			transfer(m, rs, COL_KANJI);
			transfer(m, rs, COL_GRAMMATICAL_TAGS);
			transfer(m, rs, COL_SENSES);
			transfer(m, rs, COL_COMMON);

			list.add(m);
		}
		st.close();
		return list;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}
