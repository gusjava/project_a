package a.entity.gus06.y.filedb1.cx.initdb.fileinfo_access;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

    public String creationDate() {return "20251126";}

    public static final String TABLE_NAME = "fileinfo_access";

    public static final String COL_MD5 = "md5";
    public static final String COL_ACCESSINFO = "accessinfo";
    public static final String COL_TIME = "time";

    public static final String DEF_MD5 = "VARCHAR(32) NOT NULL";
    public static final String DEF_ACCESSINFO = "VARCHAR(200) NULL";
    public static final String DEF_TIME = "DATETIME NOT NULL";

    public void p(Object obj) throws Exception {
        Connection cx = (Connection) obj;
        String sql = "CREATE TABLE "+TABLE_NAME+" ("
            +COL_MD5+" "+DEF_MD5+", "
            +COL_ACCESSINFO+" "+DEF_ACCESSINFO+", "
            +COL_TIME+" "+DEF_TIME+")";
        execute(cx, sql);
    }

    private void execute(Connection cx, String sql) throws SQLException {
        Statement st = cx.createStatement();
        st.execute(sql);
        st.close();
    }
}