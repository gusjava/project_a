package a.entity.gus06.moduledev.refactor.rename;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140926";}
	
	public static final String MODULE = "Module.java";


	private Service prepare;
	private Service rewrite;
	private Service clear;

	private File dir;
	
	

	public EntityImpl() throws Exception
	{
		prepare = Outside.service(this,"gus06.moduledev.refactor.rename.prepare");
		rewrite = Outside.service(this,"gus06.moduledev.refactor.rename.rewritefile");
		clear = Outside.service(this,"gus06.dir.perform.clear");
		dir = (File) Outside.resource(this,"path#path.dev.managerdir2");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		String[] o = (String[]) prepare.t(obj);
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String name1 = o[0];
		String name2 = o[1];
		
		File dir1 = new File(dir,name1.replace(".",File.separator));
		File dir2 = new File(dir,name2.replace(".",File.separator));
		
		File file1 = new File(dir1,MODULE);
		File file2 = new File(dir2,MODULE);
		
		if(!file1.isFile()) throw new Exception("Module file not found: "+file1);
		if(file2.exists()) throw new Exception("Module file already exist: "+file2);
		
		dir2.mkdirs();
		refactorFile(file1,file2,name1,name2);
		
		deleteFile(file1);
		clearDir(dir1);
		
		refactorDir(dir,name1,name2);
	}
	
	
	
	
	private void refactorDir(File dir, String name1, String name2) throws Exception
	{
		File[] ff = dir.listFiles();
		for(File f:ff)
		{
			if(f.isFile()) refactorFile(f,name1,name2);
			else refactorDir(f,name1,name2);
		}
	}
	


	private void refactorFile(File file, String name1, String name2) throws Exception
	{refactorFile(file,file,name1,name2);}
	
	
	private void refactorFile(File file1, File file2, String name1, String name2) throws Exception
	{rewrite.p(new Object[]{file1,file2,name1,name2});}
	
	
	
	private void deleteFile(File file) throws Exception
	{
		boolean r = file.delete();
		if(!r) throw new Exception("Failed to delete file: "+file);
	}
	
	
	private void clearDir(File dir) throws Exception
	{clear.p(dir);}
}