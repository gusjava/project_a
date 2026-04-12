package a.entity.gus06.appli.entityaccess.engine.download.rebuildsrc;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150302";}


	public static final String KEY_ENTITYDIR = "entitydir";
	public static final String KEY_ENTITYNAME = "entityname";
	public static final String KEY_FILENUMBER = "filenumber";
	public static final String KEY_FILENAME = "filename";
	public static final String KEY_FILECONTENT = "filecontent";
	

	
	
	private Service optionManager;
	private Service analyzer;
	private Service removeFiles;
	
	
	public EntityImpl() throws Exception
	{
		optionManager = Outside.service(this,"gus06.sys.option.manager");
		analyzer = Outside.service(this,"gus06.appli.entityaccess.engine.download.analyze");
		removeFiles = Outside.service(this,"gus06.dir.perform.removefiles0");
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) obj;
		Map map = (Map) analyzer.t(src);
		
		File entityDir = new File(option(KEY_ENTITYDIR));
		
		String entityName = get(map,KEY_ENTITYNAME);
		String fileNumber = get(map,KEY_FILENUMBER);
		
		String relPath = ("a.entity."+entityName).replace(".",File.separator);
		File packageDir = new File(entityDir,relPath);
		
		packageDir.mkdirs();
		removeFiles.p(packageDir);
		
		int nb = Integer.parseInt(fileNumber);
		for(int i=0;i<nb;i++)
		{
			String fileName = get(map,KEY_FILENAME+":"+i);
			String fileContent = get(map,KEY_FILECONTENT+":"+i);
			
			File file = new File(packageDir,fileName);
			PrintStream p = new PrintStream(file);
			p.print(fileContent);
			p.close();
		}
		return entityName;
	}
	
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
	
	
	
	private String option(String key) throws Exception
	{
		String v = (String) optionManager.r(key);
		if(v==null) throw new Exception("Undefined option: "+key);
		return v;
	}
}