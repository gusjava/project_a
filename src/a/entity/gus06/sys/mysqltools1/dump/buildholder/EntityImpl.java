package a.entity.gus06.sys.mysqltools1.dump.buildholder;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230218";}

	public static final String URL = "url";
	public static final String USER = "user";
	public static final String PWD = "pwd";
	public static final String PATH_DUMPDIR = "path.dumpdir";
	public static final String DUMP_BACKUP_ENABLED = "dump.backup.enabled";
	public static final String DUMP_RESTORE_ENABLED = "dump.restore.enabled";
	

	private Service buildDump;
	private Service names0;
	private Service now;

	public EntityImpl() throws Exception
	{
		buildDump = Outside.service(this,"factory#gus06.sys.mysqltools1.dump");
		names0 = Outside.service(this,"gus06.dir.listing0.names0.aslist");
		now = Outside.service(this,"gus06.time.now");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File rootDir = (File) o[0];
		File mysqlDir = (File) o[1];
		Map map = (Map) o[2];
		
		if(rootDir==null) throw new Exception("Mysql root dir not defined");
		if(!rootDir.isDirectory()) throw new Exception("Mysql root dir not found");
		
		File binDir = new File(mysqlDir,"bin");
		if(!binDir.isDirectory()) throw new Exception("Mysql bin dir not found");
		
		String url = get0(map, URL);
		String user = get0(map, USER);
		String pwd = get0(map, PWD);
		String dumpPath = get0(map, PATH_DUMPDIR);
		
		boolean backupEnabled = getBool(map, DUMP_BACKUP_ENABLED, false);
		boolean restoreEnabled = getBool(map, DUMP_RESTORE_ENABLED, false);
		
		File dumpDir = dumpPath!=null ? new File(dumpPath) : new File(rootDir,"dumps");
		if(!dumpDir.isDirectory()) dumpDir.mkdirs();
		
		return new Holder(binDir, dumpDir, url, user, pwd, backupEnabled, restoreEnabled);
	}
	
	private class Holder implements V, R, G, F
	{
		private File binDir;
		private File dumpDir;
		private File tempDir;
		private String url;
		private String user;
		private String pwd;
		
		private File sqlFile;
		private boolean backupEnabled;
		private boolean restoreEnabled;
		
		private Object dump;
		
		public Holder(
			File binDir, File dumpDir, 
			String url, String user, String pwd,
			boolean backupEnabled, boolean restoreEnabled) throws Exception
		{
			this.binDir = binDir;
			this.dumpDir = dumpDir;
			this.url = url;
			this.user = user;
			this.pwd = pwd;
			this.backupEnabled = backupEnabled;
			this.restoreEnabled = restoreEnabled;
			
			tempDir = new File(dumpDir,"temp");
			if(!tempDir.isDirectory()) tempDir.mkdirs();
		
			dump = buildDump.g();
			((V) dump).v("binDir",binDir);
			((V) dump).v("url",url);
			((V) dump).v("user",user);
			((V) dump).v("pwd",pwd);
		}
		
		public Object g() throws Exception
		{
			return names0.t(dumpDir);
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj.equals("backupEnabled")) return backupEnabled;
			if(obj.equals("restoreEnabled")) return restoreEnabled;
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
			
		public Object r(String key) throws Exception
		{
			if(key.equals("binDir")) return binDir;
			if(key.equals("dumpDir")) return dumpDir;
			if(key.equals("tempDir")) return tempDir;
			if(key.equals("url")) return url;
			if(key.equals("user")) return user;
			if(key.equals("pwd")) return pwd;
			if(key.equals("sqlFile")) return sqlFile;
			
			if(key.equals("keys"))
				return new String[]{
					"binDir","dumpDir","tempDir",
					"url","user","pwd",
					"sqlFile"};
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("backup")) {backup((String) obj);return;}
			if(key.equals("backupTemp")) {backupTemp((String) obj);return;}
			if(key.equals("restore")) {restore((Object[]) obj);return;}
		}
		
		
		
		
		
		
		private void backup(String dbName) throws Exception
		{
			if(!backupEnabled) throw new Exception("Backup is not enabled");
			
			String fileName = dbName+"_"+now.g()+".sql";
			sqlFile = new File(dumpDir, fileName);
			
			((V) dump).v("dbName",dbName);
			((V) dump).v("sqlFile",sqlFile);
			((P) dump).p("backup");
		}
		
		private void backupTemp(String dbName) throws Exception
		{
			if(!backupEnabled) throw new Exception("Backup is not enabled");
			
			String fileName = dbName+"_"+now.g()+".sql";
			sqlFile = new File(tempDir, fileName);
			
			((V) dump).v("dbName",dbName);
			((V) dump).v("sqlFile",sqlFile);
			((P) dump).p("backup");
		}
		
		private void restore(Object[] data) throws Exception
		{
			if(data.length!=2) throw new Exception("Wrong data number: "+data.length);
			if(!restoreEnabled) throw new Exception("Restore is not enabled");
			
			String dbName = (String) data[0];
			sqlFile = findExistingSqlFile(data[1], dbName);
			
			((V) dump).v("dbName",dbName);
			((V) dump).v("sqlFile",sqlFile);
			((P) dump).p("restore");
		}
		
		
		
		
		
		
		private File findExistingSqlFile(Object data, String dbName) throws Exception
		{
			if(data instanceof File)
			{
				File file = (File) data;
				if(file.isFile()) return file;
				throw new Exception("Sql file not found: "+file);
			}
			if(data instanceof String)
			{
				String filePath = (String) data;
				File f0 = new File(filePath);
				if(f0.isFile()) return f0;
				
				String fileName = (String) data;
				File f1 = new File(dumpDir, fileName);
				if(f1.isFile()) return f1;
				
				String fileName0 = (String) data;
				File f2 = new File(dumpDir, fileName0+".sql");
				if(f2.isFile()) return f2;
				
				String timeStamp = (String) data;
				File f3 = new File(dumpDir, dbName+"_"+timeStamp+".sql");
				if(f3.isFile()) return f3;
				
				throw new Exception("Sql file not found for info: "+data);
			}
			throw new Exception("Invalid data type: "+data.getClass().getName());
		}
	}
	
	private String get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	private boolean getBool(Map map, String key, boolean defaultValue) throws Exception
	{
		if(!map.containsKey(key)) return defaultValue;
		return Boolean.parseBoolean((String) map.get(key));
	}
}