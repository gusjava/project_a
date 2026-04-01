package a.entity.gus06.y.filedb1.cx.initdb.isbn_author_link;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

    public String creationDate() {return "20251126";}

    public static final String TABLE_NAME = "isbn_author_link";

    public static final String COL_ID = "id";
    public static final String COL_ISBN = "isbn";
    public static final String COL_AUTHOR_ID = "author_id";

    public static final String DEF_ID = "INT PRIMARY KEY NOT NULL";
    public static final String DEF_ISBN = "VARCHAR(50) NOT NULL";
    public static final String DEF_AUTHOR_ID = "INT NOT NULL";

    public void p(Object obj) throws Exception {
        Connection cx = (Connection) obj;
        String sql =
            "CREATE TABLE " + TABLE_NAME + " ("
            + COL_ID + " " + DEF_ID + ", "
            + COL_ISBN + " " + DEF_ISBN + ", "
            + COL_AUTHOR_ID + " " + DEF_AUTHOR_ID + ")";
        execute(cx, sql);
    }

    private void execute(Connection cx, String sql) throws SQLException {
        Statement st = cx.createStatement();
        st.execute(sql);
        st.close();
    }
}


/* ============================================================
   ebook_author
   ============================================================ */