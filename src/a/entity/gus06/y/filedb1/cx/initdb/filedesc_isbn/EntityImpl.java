package a.entity.gus06.y.filedb1.cx.initdb.filedesc_isbn;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

    public String creationDate() {return "20251126";}

    public static final String TABLE_NAME = "filedesc_isbn";

    public static final String COL_MD5 = "md5";
    public static final String COL_ISBN = "isbn";
    public static final String COL_CAPTURE_TYPE = "capture_type";
    public static final String COL_TRUST = "trust";

    public static final String DEF_MD5 = "VARCHAR(32) PRIMARY KEY NOT NULL";
    public static final String DEF_ISBN = "VARCHAR(50) NULL";
    public static final String DEF_CAPTURE_TYPE = "VARCHAR(50) NOT NULL";
    public static final String DEF_TRUST = "INT NULL";

    public void p(Object obj) throws Exception {
        Connection cx = (Connection) obj;
        String sql = "CREATE TABLE "+TABLE_NAME+" ("
            +COL_MD5+" "+DEF_MD5+", "
            +COL_ISBN+" "+DEF_ISBN+", "
            +COL_CAPTURE_TYPE+" "+DEF_CAPTURE_TYPE+", "
            +COL_TRUST+" "+DEF_TRUST+")";
        execute(cx, sql);
    }

    private void execute(Connection cx, String sql) throws SQLException {
        Statement st = cx.createStatement();
        st.execute(sql);
        st.close();
    }
}

/* ============================================================
   fileinfo_tag
   ============================================================ */