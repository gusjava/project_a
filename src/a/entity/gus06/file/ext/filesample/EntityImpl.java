package a.entity.gus06.file.ext.filesample;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import a.framework.*;


public class EntityImpl implements Entity, T, R, G {

	public String creationDate() {return "20180410";}

	
	private File tmpDir;
	
	public EntityImpl() throws Exception
	{
		tmpDir = (File) Outside.resource(this,"defaultdir");
		tmpDir.mkdirs();
	}
	
	
	public Object g() throws Exception
	{return tmpDir;}
	
	
	public Object r(String key) throws Exception
	{return t(key);}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String ext = (String) obj;
		String fileName = ext.equals("")?"tmp":"tmp."+ext;
		
		File file = new File(tmpDir,fileName);
		if(!file.exists()) createFile(file);
		return file;
	}
	
	
	private void createFile(File file) throws Exception
	{
		try{file.createNewFile();}
		catch(Exception e)
		{
			String message = "Failed to create file: "+file;
			throw new Exception(message,e);
		}
	}
}