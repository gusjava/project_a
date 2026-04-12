package a.entity.gus.y.h2cache1.check;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20231206";}
	
	public static final String TABLENAME_INITIALIZED = "initialized";

	public static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";

	private Service logger;
	
	public EntityImpl() throws Exception {
		logger = Outside.service(this, "logger");
	}

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Object init = o[1];
		check(cx, init);
	}

	private void check(Connection cx, Object init) throws Exception {
		boolean hasTable = queryOne(cx, "SHOW TABLES")!=null;
		if(!hasTable) {
			log("Database'structure not found");
			log("Initializing structure...");
			createAll(cx,init);
			return;
		}
		Date dateInitialized = retrieveInitializedDate(cx);
		Date dateLatestChange = parseDate((String) ((G) init).g());
		
		if (dateInitialized == null || dateLatestChange == null || dateLatestChange.after(dateInitialized)) {
			log("Database'structure is out dated");
			log("Resetting structure...");
			dropAll(cx);
			createAll(cx,init);
			return;
		}
		log("Database'structure is up to date");
	}
	
	/*
	 * SQL QUERIES
	 */
	
	private void dropAll(Connection cx) throws Exception {
		String sql = "DROP ALL OBJECTS";
		execute(cx, sql);
	}
	
	private void createAll(Connection cx, Object init) throws Exception {
		((P) init).p(cx);
		String sql1 = "CREATE TABLE " + TABLENAME_INITIALIZED + " (date DATETIME)";
		execute(cx, sql1);
		String sql2 = "INSERT INTO " + TABLENAME_INITIALIZED + " (date) VALUES ('" + formatDate(new Date()) + "')";
		execute(cx, sql2);
	}
	
	private Date retrieveInitializedDate(Connection cx) throws Exception {
		String sql1 = "CREATE TABLE IF NOT EXISTS " + TABLENAME_INITIALIZED + " (date DATETIME)";
		execute(cx, sql1);
		String sql2 = "SELECT date FROM " + TABLENAME_INITIALIZED;
		return (Date) queryOne(cx, sql2);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}

	private Object queryOne(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);
		Object result = rs.next() ? rs.getObject(1) : null;
		st.close();
		return result;
	}

	/*
	 * DATE
	 */

	private String formatDate(Date value)
	{return new SimpleDateFormat(FORMAT_DATE).format(value);}

	private Date parseDate(String s) throws ParseException
	{
		if (s == null) return null;
		return new SimpleDateFormat(FORMAT_DATE).parse(s);
	}
	
	/*
	 * LOGGER
	 */
	
	private void log(String msg) throws Exception
	{logger.p(new Object[] {this, msg});}
}
