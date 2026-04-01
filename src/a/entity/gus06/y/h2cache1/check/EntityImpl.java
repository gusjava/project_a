package a.entity.gus06.y.h2cache1.check;

import a.framework.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250707";}
	
	public static final String TABLENAME_INITIALIZED = "initialized";

	public static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Object init = o[1];
		check(cx, init);
	}
	
	private void check(Connection cx, Object init) throws Exception
	{
		boolean hasTable = queryOne(cx, "SHOW TABLES")!=null;
		if(!hasTable)
		{
			createAll(cx,init);
			return;
		}
		Date dateInitialized = retrieveInitializedDate(cx);
		Date dateLatestChange = parseDate((String) ((G) init).g());
		
		if (dateInitialized == null || dateLatestChange == null || dateLatestChange.after(dateInitialized))
		{
			dropAll(cx);
			createAll(cx,init);
			return;
		}
	}
	
	/*
	 * SQL QUERIES
	 */
	
	private void dropAll(Connection cx) throws Exception
	{
		String sql = "DROP ALL OBJECTS";
		execute(cx, sql);
	}
	
	private void createAll(Connection cx, Object init) throws Exception
	{
		((P) init).p(cx);
		String sql1 = "CREATE TABLE " + TABLENAME_INITIALIZED + " (date DATETIME)";
		execute(cx, sql1);
		String sql2 = "INSERT INTO " + TABLENAME_INITIALIZED + " (date) VALUES ('" + formatDate(new Date()) + "')";
		execute(cx, sql2);
	}
	
	private Date retrieveInitializedDate(Connection cx) throws Exception
	{
		String sql1 = "CREATE TABLE IF NOT EXISTS " + TABLENAME_INITIALIZED + " (date DATETIME)";
		execute(cx, sql1);
		String sql2 = "SELECT date FROM " + TABLENAME_INITIALIZED;
		return (Date) queryOne(cx, sql2);
	}

	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}

	private Object queryOne(Connection cx, String sql) throws SQLException
	{
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
	{
		return new SimpleDateFormat(FORMAT_DATE).format(value);
	}

	private Date parseDate(String s) throws ParseException
	{
		if (s == null) return null;
		return new SimpleDateFormat(FORMAT_DATE).parse(s);
	}
}