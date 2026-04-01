package a.entity.gus06.file.jar.extractor2.a;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import a.framework.*;

public class EntityImpl implements Entity, V, E {

	public String creationDate() {return "20140910";}

    
	private Service ioTransfer;

	private File jarFile;
	private Map fileMap;
	
	
	public EntityImpl() throws Exception
	{
		ioTransfer = Outside.service(this,"gus06.io.transfer");
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("jarFile")) {jarFile = (File) obj;return;}
		if(key.equals("fileMap")) {fileMap = (Map) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void e() throws Exception
	{extract();}
	
	
	
	private void extract() throws Exception
	{
		JarFile jar = new JarFile(jarFile,true,JarFile.OPEN_READ);
		
		Enumeration en = jar.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry) en.nextElement();
			String name = entry.getName();
        	
			if(!entry.isDirectory() && fileMap.containsKey(name))
			{
				File f = (File) fileMap.get(name);
				InputStream is = jar.getInputStream(entry);
				writeToFile(is,f);
			}
		}
		jar.close();
	}
    
    
    
	private void writeToFile(InputStream is, File file) throws Exception
	{
		file.getParentFile().mkdirs();
		OutputStream os = new FileOutputStream(file);  
		ioTransfer.p(new Object[]{is,os});
	}
}
