package a.entity.gus06.sys.base2.builder.datamap;

import a.framework.*;
import java.io.File;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150330";}
	
	public static final String PATH_DATA = "path.data";
	public static final String PATH_BACKUP = "path.backup";
	public static final String PATH_INDEX = "path.index";
	public static final String PATH_STRUCTURE = "path.structure";
	
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		File infoFile = new File(dir,"info.properties");
		Properties info = buildInfo(infoFile);
		
		File dataDir = new File(dir,"data");
		File backupDir = new File(dir,"backup");
		File indexDir = new File(dir,"index");
		File structureDir = new File(dir,"structure");
		
		Map stateMap = new HashMap();
		
		stateMap.put(PATH_DATA,getDir1(info,dataDir,PATH_DATA));
		stateMap.put(PATH_BACKUP,getDir1(info,backupDir,PATH_BACKUP));
		stateMap.put(PATH_INDEX,getDir1(info,indexDir,PATH_INDEX));
		stateMap.put(PATH_STRUCTURE,getDir1(info,structureDir,PATH_STRUCTURE));
		
		return stateMap;
	}
	
	
	private File getDir1(Properties info, File defaultDir, String key)
	{
		File dir = getDir(info,defaultDir,key);
		dir.mkdirs();
		return dir;
	}
	
	private File getDir(Properties info, File defaultDir, String key)
	{
		if(!info.containsKey(key)) return defaultDir;
		return new File(info.getProperty(key));
	}
	
	
	private Properties buildInfo(File f) throws Exception
	{
		Properties p = new Properties();
		if(!f.exists())
		{
			f.createNewFile();
			return p;
		}
		FileInputStream fis = new FileInputStream(f);
		p.load(fis);
		fis.close();
		return p;	
	}
}