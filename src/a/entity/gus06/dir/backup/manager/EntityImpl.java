package a.entity.gus06.dir.backup.manager;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150329";}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String now() {return sdf.format(new Date());}


	private Service dirToSet;
	private Service zipBuilder;
	
	public EntityImpl() throws Exception
	{
		dirToSet = Outside.service(this,"gus06.dir.children.dirtoset.name0");
		zipBuilder = Outside.service(this,"*gus06.file.zip.builder1");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Manager((File[]) obj);}
	
	
	
	private void performBackup(File zipFile, File dataDir, Object progress)
	{
		try
		{
			zipBuilder.v("output",zipFile);
			zipBuilder.v("input",dataDir);
			zipBuilder.v("progress",progress);
		
			zipBuilder.p("zip");
		}
		catch(Exception e)
		{Outside.err(this,"performBackup(File,File,Object)",e);}
	}
	
	
	
	private class Manager implements V, R, G, Runnable
	{
		private File dataDir;
		private File backupDir;
		private Object progress;
		
		public Manager(File[] d) throws Exception
		{
			if(d.length!=2) throw new Exception("Wrong data number: "+d.length);
			dataDir = d[0];
			backupDir = d[1];
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("progress")) {progress = obj;return;}
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{return backup(key);}
		
		
		public Object g() throws Exception
		{return dirToSet.t(backupDir);}
		
		
		
		public void run()
		{
			File zipFile = backup(now());
			performBackup(zipFile,dataDir,progress);
		}
		
		
		private File backup(String name)
		{return new File(backupDir,name+".zip");}
	}
}
