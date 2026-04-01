package a.entity.gus06.appli.entityaccess.entitydir.buildmap.md5dir;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150814";}


	public static final String MAINNAME = "EntityImpl.java";
	
	public static final FileFilter DIRFILTER = new FileFilter(){
		public boolean accept(File f){return f.isDirectory();}
	};
	

	private Service findRootDir;
	private Service dirToSrcmd5;
	
	
	public EntityImpl() throws Exception
	{
		findRootDir = Outside.service(this,"gus06.appli.entityaccess.entitydir.find.pseudoroot");
		dirToSrcmd5 = Outside.service(this,"gus06.entitydev.dirtosrcmd5.full");
	}
	
	
	public Object g() throws Exception
	{
		File rootDir = (File) findRootDir.g();
		
		Map map = new HashMap();
		if(rootDir!=null && rootDir.isDirectory())
			analyzeDir(map,rootDir);
		return map;
	}
	
	
	
	private void analyzeDir(Map map, File dir) throws Exception
	{
		File mainFile = new File(dir,MAINNAME);
		if(mainFile.exists())
		{
			String md5 = (String) dirToSrcmd5.t(dir);
			map.put(md5,dir);
		}
		
		File[] dd = dir.listFiles(DIRFILTER);
		for(File d:dd) analyzeDir(map,d);
	}
}
