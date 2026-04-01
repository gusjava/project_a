package a.entity.gus06.url.build.tmpfile;

import a.framework.*;
import java.net.URL;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170202";}
	
	public static final String DEFAULT_FILENAME = "default";


	private Service normalizeFileName;
	private Service now;
	
	private File storeDir;
	
	public EntityImpl() throws Exception
	{
		normalizeFileName = Outside.service(this,"gus06.string.transform.normalize.filename");
		now = Outside.service(this,"gus06.time.now");
		
		storeDir = (File) Outside.resource(this,"defaultdir");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		URL url = (URL) obj;
		
		String fileName = normalizeName(url.getFile());
		if(fileName==null || fileName.equals("")) fileName = DEFAULT_FILENAME;
		
		File dir = new File(storeDir,now());
		dir.mkdirs();
		
		File file = new File(dir,fileName);
		
		try{file.createNewFile();}
		catch(Exception e)
		{
			file = new File(dir,DEFAULT_FILENAME);
			file.createNewFile();
		}
		
		return file;
	}
	
	private String normalizeName(String name) throws Exception
	{return (String) normalizeFileName.t(name);}
	
	private String now() throws Exception
	{return (String) now.g();}
}
