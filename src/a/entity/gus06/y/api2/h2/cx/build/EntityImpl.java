package a.entity.gus06.y.api2.h2.cx.build;

import java.io.File;
import org.h2.jdbcx.JdbcDataSource;
import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {
	
	public String creationDate() {return "20250701";}
	
	public static final String DEFAULT_USER = "sa";
	public static final String DEFAULT_PWD = "";
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Invalid null obj for cx building");
		if(obj instanceof File) return buildFromFile((File) obj);
		if(obj instanceof String) return buildFromString((String) obj);
		if(obj instanceof Object[]) return buildFromArray((Object[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Connection buildFromFile(File f) throws Exception
	{
		String url = fileToUrl(f);
		return build(url, DEFAULT_USER, DEFAULT_PWD);
	}
	
	private Connection buildFromString(String url) throws Exception
	{
		return build(url, DEFAULT_USER, DEFAULT_PWD);
	}
	
	private Connection buildFromArray(Object[] o) throws Exception
	{
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);

		String url = toUrl(o[0]);
		String user = (String) o[1];
		String pwd = (String) o[2];
		
		return build(url, user, pwd);
	}
	
	private Connection build(String url, String user, String pwd) throws Exception
	{
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(url);
		dataSource.setUser(user);
		dataSource.setPassword(pwd);

		return dataSource.getConnection();
	}
	
	private String toUrl(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return fileToUrl((File) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String fileToUrl(File file)
	{
		file.getParentFile().mkdirs();
		String path = file.getAbsolutePath().replace("\\","/");
		if(path.endsWith(".mv.db")) path = path.substring(0, path.length()-6);
		
		return "jdbc:h2:file:" + path + ";MODE=MySQL;";
	}
}