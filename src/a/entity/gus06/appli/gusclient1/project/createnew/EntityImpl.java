package a.entity.gus06.appli.gusclient1.project.createnew;

import a.framework.*;
import java.io.File;
import java.util.Properties;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140830";}

	public static final String FILENAME_INFO = "info.properties";
	
	public static final String FILENAME_PROP = "prop";
	public static final String FILENAME_MAPPING = "mapping";
	public static final String FILENAME_JAR_LOCATION = "jar_location";
	public static final String FILENAME_DLL_LOCATION = "dll_location";
	
	public static final String DIRNAME_RES = "resources";
	public static final String DIRNAME_ICON = "icon";
	public static final String DIRNAME_LING = "ling";
	public static final String DIRNAME_STORE = "store";
	public static final String DIRNAME_JAR = "jar";
	public static final String DIRNAME_DLL = "dll";



	private Service getNow;
	private Service manager;
	private Service idToDir;
	private Service writeProp;
	private Service findPseudo;
	
	
	public EntityImpl() throws Exception
	{
		getNow = Outside.service(this,"gus06.time.now");
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToDir = Outside.service(this,"gus06.appli.gusclient1.project.idtodir");
		writeProp = Outside.service(this,"gus06.file.write.properties");
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String id = (String) obj;
		
		File dir0 = (File) idToDir.t(id);
		
		File dir1 = new File(dir0,DIRNAME_RES);
		File dir2a = new File(dir1,DIRNAME_ICON);
		File dir2b = new File(dir1,DIRNAME_LING);
		File dir2c = new File(dir1,DIRNAME_STORE);
		File dir2d = new File(dir1,DIRNAME_JAR);
		File dir2e = new File(dir1,DIRNAME_DLL);
		
		dir2a.mkdirs();
		dir2b.mkdirs();
		dir2c.mkdirs();
		dir2d.mkdirs();
		dir2e.mkdirs();
		
		
		File fileInfo = new File(dir0,FILENAME_INFO);
		handleFileInfo(fileInfo);
		
		File fileProp = new File(dir1,FILENAME_PROP);
		handleFileProp(fileProp);
		
		File fileMapping = new File(dir1,FILENAME_MAPPING);
		fileMapping.createNewFile();
		
		File fileJarLocation = new File(dir1,FILENAME_JAR_LOCATION);
		fileJarLocation.createNewFile();
		
		File fileDllLocation = new File(dir1,FILENAME_DLL_LOCATION);
		fileDllLocation.createNewFile();
		
		manager.p(id);
	}
	
	
	
	
	
	
	private void handleFileInfo(File f) throws Exception
	{
		Properties p = new Properties();
		
		String now = (String) getNow.g();
		String pseudo = (String) findPseudo.g();
		
		p.put("description","Put a description here");
		p.put("creationtime",now);
		p.put("author",pseudo);
		
		writeProp.p(new Object[]{f,p});
	}
	
	
	
	
	private void handleFileProp(File f) throws Exception
	{
		Properties p = new Properties();
		
		p.put("model","p_build");
		
		writeProp.p(new Object[]{f,p});
	}
	
}
