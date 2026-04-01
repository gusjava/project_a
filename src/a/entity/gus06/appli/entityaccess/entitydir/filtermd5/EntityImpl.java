package a.entity.gus06.appli.entityaccess.entitydir.filtermd5;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150302";}


	public static final String MAINNAME = "EntityImpl.java";
	
	public static final FileFilter DIRFILTER = new FileFilter(){
		public boolean accept(File f){return f.isDirectory();}
	};
	

	private Service findRootDir;
	private Service dirToSrcmd5;
	
	
	public EntityImpl() throws Exception
	{
		findRootDir = Outside.service(this,"gus06.appli.entityaccess.entitydir.find.entityroot");
		dirToSrcmd5 = Outside.service(this,"gus06.entitydev.dirtosrcmd5.full");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List md5List = (List) obj;
		
		File rootDir = (File) findRootDir.g();
		if(rootDir==null) return null;
		
		if(!rootDir.isDirectory()) rootDir.mkdirs();
		
		List md5List1 = new ArrayList(md5List);
		if(rootDir.isDirectory()) analyzeDir(md5List1,rootDir);
		return md5List1;
	}
	
	
	
	private void analyzeDir(List md5List1, File dir) throws Exception
	{
		File mainFile = new File(dir,MAINNAME);
		if(mainFile.exists())
		{
			String md5 = (String) dirToSrcmd5.t(dir);
			if(md5List1.contains(md5))
			md5List1.remove(md5);
		}
		
		File[] dd = dir.listFiles(DIRFILTER);
		for(File d:dd) analyzeDir(md5List1,d);
	}
}