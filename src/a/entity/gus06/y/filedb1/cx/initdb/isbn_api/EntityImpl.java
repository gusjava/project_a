package a.entity.gus06.y.filedb1.cx.initdb.isbn_api;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

    public String creationDate() {return "20251126";}

    public static final String TABLE_NAME = "isbn_api";

    public static final String COL_ID = "id";
    public static final String COL_ISBN = "isbn";
    public static final String COL_API = "api";
    public static final String COL_RESPONSE_JSON = "response_json";
    public static final String COL_CREATED = "created";

    public static final String DEF_ID = "INT PRIMARY KEY NOT NULL";
    public static final String DEF_ISBN = "VARCHAR(50) NOT NULL";
    public static final String DEF_API = "VARCHAR(100) NOT NULL";
    public static final String DEF_RESPONSE_JSON = "TEXT NULL";
    public static final String DEF_CREATED = "DATETIME NOT NULL";

    public void p(Object obj) throws Exception {
        Connection cx = (Connection) obj;
        String sql =
            "CREATE TABLE " + TABLE_NAME + " ("
            + COL_ID + " " + DEF_ID + ", "
            + COL_ISBN + " " + DEF_ISBN + ", "
            + COL_API + " " + DEF_API + ", "
            + COL_RESPONSE_JSON + " " + DEF_RESPONSE_JSON + ", "
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
   isbn_author_link
   ============================================================ */