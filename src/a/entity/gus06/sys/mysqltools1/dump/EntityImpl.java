package a.entity.gus06.sys.mysqltools1.dump;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, R, V, P {

	public String creationDate() {return "20230218";}

	public static final String FILENAME_MYSQLDUMP = "mysqldump.exe";
	public static final String FILENAME_MYSQL = "mysql.exe";
	public static final String DEFAULT_USERNAME = "root";


	private Service perform;
	private Service parseUrl;
	
	private File binDir;
	private File sqlFile;
	private String dbName;
	private String url;
	private String user;
	private String pwd;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.exec.asbatch");
		parseUrl = Outside.service(this,"gus06.jdbc.url.parse");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String cmd = (String) obj;
		if(cmd.equals("backup")) backup();
		else if(cmd.equals("restore")) restore();
		else throw new Exception("Unsupported cmd: "+cmd);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("binDir")) return binDir;
		if(key.equals("sqlFile")) return sqlFile;
		if(key.equals("dbName")) return dbName;
		if(key.equals("url")) return url;
		if(key.equals("user")) return user;
		if(key.equals("pwd")) return pwd;
		if(key.equals("cmdBackup")) return buildCmdBackup();
		if(key.equals("cmdRestore")) return buildCmdRestore();
		
		if(key.equals("keys")) 
			return new String[]{
				"binDir","sqlFile",
				"dbName","url","user","pwd",
				"cmdBackup","cmdRestore"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("binDir")) {binDir = (File) obj;return;}
		if(key.equals("sqlFile")) {sqlFile = (File) obj;return;}
		if(key.equals("dbName")) {dbName = (String) obj;return;}
		if(key.equals("url")) {url = (String) obj;return;}
		if(key.equals("user")) {user = (String) obj;return;}
		if(key.equals("pwd")) {pwd = (String) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private File buildMysqlDump() throws Exception
	{
		if(binDir==null) throw new Exception("Bin dir has not been initialized yet");
		File exeFile = new File(binDir,FILENAME_MYSQLDUMP);
		if(!exeFile.isFile()) throw new Exception("MysqlDump Exe file not found: "+exeFile);
		return exeFile;
	}
	
	private File buildMysql() throws Exception
	{
		if(binDir==null) throw new Exception("Bin dir has not been initialized yet");
		File exeFile = new File(binDir,FILENAME_MYSQL);
		if(!exeFile.isFile()) throw new Exception("Mysql Exe file not found: "+exeFile);
		return exeFile;
	}
	
	private String p(File file)
	{return "\""+file+"\"";}
	
	
	private boolean isEmpty(String s)
	{return s==null || s.trim().equals("");}
	
	
	private String buildCmdBackup() throws Exception
	{
		File exeFile = buildMysqlDump();
		if(dbName==null) throw new Exception("DB name not initialized yet");
		if(sqlFile==null) throw new Exception("SQL file not initialized yet");
		
		StringBuffer b = new StringBuffer();
		b.append(p(exeFile));
		if(!isEmpty(url))
		{
			Map m = (Map) parseUrl.t(url);
			int port = (Integer) m.get("port");
			String host = (String) m.get("host");
			b.append(" --host="+host);
			b.append(" --port="+port);
		}
		if(!isEmpty(user)) b.append(" --user="+user);
		else b.append(" --user=root");
		if(!isEmpty(pwd)) b.append(" --password="+pwd);
		b.append(" "+dbName);
		b.append(" > "+p(sqlFile));
		return b.toString();
	}
	
	
	private String buildCmdRestore() throws Exception
	{
		File exeFile = buildMysql();
		if(dbName==null) throw new Exception("DB name not initialized yet");
		if(sqlFile==null) throw new Exception("SQL file not initialized yet");
		
		StringBuffer b = new StringBuffer();
		b.append(p(exeFile));
		if(!isEmpty(url))
		{
			Map m = (Map) parseUrl.t(url);
			int port = (Integer) m.get("port");
			String host = (String) m.get("host");
			b.append(" --host="+host);
			b.append(" --port="+port);
		}
		if(!isEmpty(user)) b.append(" --user="+user);
		else b.append(" --user=root");
		if(!isEmpty(pwd)) b.append(" --password="+pwd);
		b.append(" "+dbName);
		b.append(" < "+p(sqlFile));
		return b.toString();
	}
	
	
	private void backup() throws Exception
	{
		perform.p(buildCmdBackup());
	}
	
	private void restore() throws Exception
	{
		perform.p(buildCmdRestore());
	}
}