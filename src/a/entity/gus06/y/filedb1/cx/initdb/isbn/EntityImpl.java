package a.entity.gus06.y.filedb1.cx.initdb.isbn;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

    public String creationDate() {return "20251126";}

    public static final String TABLE_NAME = "isbn";

    public static final String COL_ISBN = "isbn";
    public static final String COL_TITLE = "title";
    public static final String COL_SUMMARY = "summary";
    public static final String COL_LANG = "lang";
    public static final String COL_PUBLISHER = "publisher";
    public static final String COL_PUBLISHED_DATE = "published_date";
    public static final String COL_CREATED = "created";

    public static final String DEF_ISBN = "VARCHAR(50) PRIMARY KEY NOT NULL";
    public static final String DEF_TITLE = "VARCHAR(300) NULL";
    public static final String DEF_SUMMARY = "TEXT NULL";
    public static final String DEF_LANG = "VARCHAR(20) NULL";
    public static final String DEF_PUBLISHER = "VARCHAR(200) NULL";
    public static final String DEF_PUBLISHED_DATE = "DATE NULL";
    public static final String DEF_CREATED = "DATETIME NOT NULL";

    public void p(Object obj) throws Exception {
        Connection cx = (Connection) obj;
        String sql =
            "CREATE TABLE " + TABLE_NAME + " ("
            + COL_ISBN + " " + DEF_ISBN + ", "
            + COL_TITLE + " " + DEF_TITLE + ", "
            + COL_SUMMARY + " " + DEF_SUMMARY + ", "
            + COL_LANG + " " + DEF_LANG + ", "
            + COL_PUBLISHER + " " + DEF_PUBLISHER + ", "
            + COL_PUBLISHED_DATE + " " + DEF_PUBLISHED_DATE + ", "
            + COL_CREATED + " " + DEF_CREATED + ")";
        execute(cx, sql);
    }

    private void execute(Connection cx, String sql) throws SQLException {
        Statement st = cx.createStatement();
        st.execute(sql);
        st.close();
    }
}


/* ============================================================
   isbn_api
   ============================================================ */