package a.entity.gus06.sys.jwpce1.engine.cx.insert.edict;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250721";}

	public static final String TABLE_NAME = "edict";

	public static final String COL_ROMAJI = "romaji";
	public static final String COL_KANA = "kana";
	public static final String COL_KANJI = "kanji";
	public static final String COL_GRAMMATICAL_TAGS = "grammatical_tags";
	public static final String COL_SENSES = "senses";
	public static final String COL_COMMON = "common";
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Object romaji = data.get(COL_ROMAJI);
		Object kana = data.get(COL_KANA);
		Object kanji = data.get(COL_KANJI);
		Object grammaticalTags = data.get(COL_GRAMMATICAL_TAGS);
		Object senses = data.get(COL_SENSES);
		Object common = data.get(COL_COMMON);
		
		if(common instanceof Boolean) common = ((Boolean) common) ? 1 : 0;

		String sql = "INSERT INTO " + TABLE_NAME + " (" 
		+ COL_ROMAJI + "," 
		+ COL_KANA + ","
		+ COL_KANJI + ","
		+ COL_GRAMMATICAL_TAGS + ","
		+ COL_SENSES + ","
		+ COL_COMMON + ") VALUES (?,?,?,?,?,?)";

		executeUpdate(cx, sql, romaji, kana, kanji, grammaticalTags, senses, common);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}