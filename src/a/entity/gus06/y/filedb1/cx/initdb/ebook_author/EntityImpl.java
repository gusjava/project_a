package a.entity.gus06.y.filedb1.cx.initdb.ebook_author;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

    public String creationDate() {return "20251126";}

    public static final String TABLE_NAME = "ebook_author";

    public static final String COL_ID = "id";
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "last_name";
    public static final String COL_VIAF = "VIAF";
    public static final String COL_ISNI = "ISNI";
    public static final String COL_ARK = "ARK";
    public static final String COL_OPENLIB_ID = "OpenLibraryID";

    public static final String DEF_ID = "INT PRIMARY KEY NOT NULL";
    public static final String DEF_FIRST_NAME = "VARCHAR(100) NULL";
    public static final String DEF_LAST_NAME = "VARCHAR(100) NULL";
    public static final String DEF_VIAF = "VARCHAR(50) NULL";
    public static final String DEF_ISNI = "VARCHAR(50) NULL";
    public static final String DEF_ARK = "VARCHAR(100) NULL";
    public static final String DEF_OPENLIB_ID = "VARCHAR(100) NULL";

    public void p(Object obj) throws Exception {
        Connection cx = (Connection) obj;
        String sql =
            "CREATE TABLE " + TABLE_NAME + " ("
            + COL_ID + " " + DEF_ID + ", "
            + COL_FIRST_NAME + " " + DEF_FIRST_NAME + ", "
            + COL_LAST_NAME + " " + DEF_LAST_NAME + ", "
            + COL_VIAF + " " + DEF_VIAF + ", "
            + COL_ISNI + " " + DEF_ISNI + ", "
            + COL_ARK + " " + DEF_ARK + ", "
            + COL_OPENLIB_ID + " " + DEF_OPENLIB_ID + ")";
        execute(cx, sql);
    }

    private void execute(Connection cx, String sql) throws SQLException {
        Statement st = cx.createStatement();
        st.execute(sql);
        st.close();
    }
}