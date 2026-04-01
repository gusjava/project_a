package a.entity.gus06.sys.tesseract1.init;

import a.framework.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210606";}

	public static final String START = "gus06/resource/gus/gyem/tesseract";


	private Service appJar;
	private Service ioTransfer;
	private Service emptyDir;

	public EntityImpl() throws Exception
	{
		appJar = Outside.service(this,"gus06.app.jarfile");
		ioTransfer = Outside.service(this,"gus06.io.transfer");
		emptyDir = Outside.service(this,"gus06.dir.op.empty");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File rootDir = (File) obj;
		rootDir.mkdirs();
		emptyDir.p(rootDir);
		
		File jarFile = (File) appJar.g();
		JarFile jar = new JarFile(jarFile,true,JarFile.OPEN_READ);
		int len = START.length();
		
		Enumeration en = jar.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry)en.nextElement();
			String name = entry.getName();
        	
			if(!entry.isDirectory() && isValid(name))
			{
				String fileName = name.substring(len);
				File f = new File(rootDir,fileName);
				InputStream is = jar.getInputStream(entry);
				writeToFile(is,f);
			}
		}
		jar.close();
	}
	
	
	private boolean isValid(String name)
	{return name.startsWith(START);}
	
	
	
	private void writeToFile(InputStream is, File file) throws Exception
	{
		file.getParentFile().mkdirs();
		OutputStream os = new FileOutputStream(file);  
		ioTransfer.p(new Object[]{is,os});
	}
}
