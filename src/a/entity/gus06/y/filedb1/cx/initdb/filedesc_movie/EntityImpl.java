package a.entity.gus06.y.filedb1.cx.initdb.filedesc_movie;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

    public String creationDate() {return "20251126";}

    public static final String TABLE_NAME = "filedesc_movie";

    public static final String COL_MD5 = "md5";
    public static final String COL_ALLOCINE_CODE = "allocine_code";
    public static final String COL_IMDB_CODE = "imdb_code";
    public static final String COL_TMDB_CODE = "tmdb_code";
    public static final String COL_TITLE = "title";
    public static final String COL_CREATED = "created";
    public static final String COL_TRUST = "trust";

    public static final String DEF_MD5 = "VARCHAR(32) PRIMARY KEY NOT NULL";
    public static final String DEF_ALLOCINE_CODE = "VARCHAR(50) NULL";
    public static final String DEF_IMDB_CODE = "VARCHAR(50) NULL";
    public static final String DEF_TMDB_CODE = "VARCHAR(50) NULL";
    public static final String DEF_TITLE = "VARCHAR(300) NULL";
    public static final String DEF_CREATED = "DATETIME NOT NULL";
    public static final String DEF_TRUST = "INT NULL";

    public void p(Object obj) throws Exception {
        Connection cx = (Connection) obj;
        String sql = "CREATE TABLE "+TABLE_NAME+" ("
            +COL_MD5+" "+DEF_MD5+", "
            +COL_ALLOCINE_CODE+" "+DEF_ALLOCINE_CODE+", "
            +COL_IMDB_CODE+" "+DEF_IMDB_CODE+", "
            +COL_TMDB_CODE+" "+DEF_TMDB_CODE+", "
            +COL_TITLE+" "+DEF_TITLE+", "
            +COL_CREATED+" "+DEF_CREATED+", "
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
   filed desc isbn
   ============================================================ */