package a.entity.gus06.sys.jwpce1.engine.cx.initdb.edict;

import a.framework.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250721";}

	public static final String TABLE_NAME = "edict";

	public static final String COL_ID = "id";
	public static final String COL_ROMAJI = "romaji";
	public static final String COL_KANA = "kana";
	public static final String COL_KANJI = "kanji";
	public static final String COL_GRAMMATICAL_TAGS = "grammatical_tags";
	public static final String COL_SENSES = "senses";
	public static final String COL_COMMON = "common";

	public static final String DEF_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
	public static final String DEF_ROMAJI = "TEXT NOT NULL";
	public static final String DEF_KANA = "TEXT NOT NULL";
	public static final String DEF_KANJI = "TEXT";
	public static final String DEF_GRAMMATICAL_TAGS = "TEXT";
	public static final String DEF_SENSES = "TEXT NOT NULL";
	public static final String DEF_COMMON = "INTEGER NOT NULL DEFAULT 0";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_ID+" "+DEF_ID+", "
				+COL_ROMAJI+" "+DEF_ROMAJI+", "
				+COL_KANA+" "+DEF_KANA+", "
				+COL_KANJI+" "+DEF_KANJI+", "
				+COL_GRAMMATICAL_TAGS+" "+DEF_GRAMMATICAL_TAGS+", "
				+COL_SENSES+" "+DEF_SENSES+", "
				+COL_COMMON+" "+DEF_COMMON+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}