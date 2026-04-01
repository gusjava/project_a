package a.entity.gus06.file.jar.extractor1.a;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import a.framework.*;

public class EntityImpl implements Entity, V, E {

	public String creationDate() {return "20140801";}

	public static final String JARPATHSEP = "/";
    
	private Service ioTransfer;

	private File jarFile;
	private File outputDir;
	private F filter;
	
	
	public EntityImpl() throws Exception
	{
		ioTransfer = Outside.service(this,"gus06.io.transfer");
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("jarFile")) {jarFile = (File) obj;return;}
		if(key.equals("outputDir")) {outputDir = (File) obj;return;}
		if(key.equals("filter")) {filter = (F) obj;return;}
		
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
			JarEntry entry = (JarEntry)en.nextElement();
			String name = entry.getName();
        	
			if(!entry.isDirectory() && isValid(name))
			{
				File f = new File(outputDir,toPath(name));
				InputStream is = jar.getInputStream(entry);
				writeToFile(is,f);
			}
		}
		jar.close();
	}
	
	

    
    
	private String toPath(String name)
	{
		if(name.startsWith(JARPATHSEP)) name = name.substring(1);
		return name.replace(JARPATHSEP,File.separator);
	}
    
    
    
    
    
	private void writeToFile(InputStream is, File file) throws Exception
	{
		file.getParentFile().mkdirs();
		OutputStream os = new FileOutputStream(file);  
		ioTransfer.p(new Object[]{is,os});
	}
    
	
		
	private boolean isValid(String name) throws Exception
	{
		if(isClass(name)) return false;
		if(isManifest(name)) return false;
		return filter==null || filter.f(name);
	}	
    
    
    
	private boolean isClass(String name)
	{return name.endsWith(".class");}
    
    
	private boolean isManifest(String name)
	{return name.startsWith("META-INF");}
}
