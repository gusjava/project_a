package a.entity.gus06.file.jar.extractor3.a;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import a.framework.*;

public class EntityImpl implements Entity, V, E {

	public String creationDate() {return "20180126";}

    public static final String JARPATHSEP = "/";
    
	private Service ioTransfer;

	private File jarFile;
	private File outputDir;
	private String root;
	
	
	public EntityImpl() throws Exception
	{
		ioTransfer = Outside.service(this,"gus06.io.transfer");
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("jarFile")) {jarFile = (File) obj;return;}
		if(key.equals("outputDir")) {outputDir = (File) obj;return;}
		if(key.equals("root")) {root = (String) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void e() throws Exception
	{extract();}
	
	
	
	
	private void extract() throws Exception
	{
		if(root==null) throw new Exception("Root has not been initialized yet");
		
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
		name = name.substring(root.length());
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
	{return name.startsWith(root);}	
}
