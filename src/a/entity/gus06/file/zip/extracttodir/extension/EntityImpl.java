package a.entity.gus06.file.zip.extracttodir.extension;

import a.framework.*;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.Enumeration;
import java.util.zip.ZipFile;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150701";}

	public static final String PATHSEP = "/";
	

	private Service ioTransfer;
	private Service autoRename;
	private Service buildZipFile;
	
	public EntityImpl() throws Exception
	{
		ioTransfer = Outside.service(this,"gus06.io.transfer");
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		File dir = (File) o[1];
		String ext = (String) o[2];
		
		try
		{
			ZipFile zipFile = (ZipFile) buildZipFile.t(file);

			Enumeration en = zipFile.entries();
			while(en.hasMoreElements())
			{
				ZipEntry entry = (ZipEntry) en.nextElement();
				String name = entry.getName();
			
				if(!entry.isDirectory() && isType(name,ext))
				{
					File f = new File(dir,toFileName(name));
					f = (File) autoRename.t(f);
				
					InputStream is = zipFile.getInputStream(entry);
					writeToFile(is,f);
				}
			}
		}
		catch(Exception e)
		{
			String message = "failed to extract from zip: "+file;
			throw new Exception(message,e);
		}
	}
	
	
	
	private boolean isType(String name, String ext)
	{return name.toLowerCase().endsWith("."+ext);}
	
	
	
	
	private String toFileName(String name)
	{
		String[] n = name.split(PATHSEP);
		return n[n.length-1];
	}
    
    
    
	private void writeToFile(InputStream is, File file) throws Exception
	{
		file.getParentFile().mkdirs();
		OutputStream os = new FileOutputStream(file);  
		ioTransfer.p(new Object[]{is,os});
	}
}