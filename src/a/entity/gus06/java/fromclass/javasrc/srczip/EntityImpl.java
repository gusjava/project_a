package a.entity.gus06.java.fromclass.javasrc.srczip;

import a.framework.*;
import java.io.File;
import java.io.InputStream;
import java.util.zip.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140820";}


	private Service isToString;
	private Service findSrcZip;
	private Service buildZipFile;

	public EntityImpl() throws Exception
	{
		isToString = Outside.service(this,"gus06.io.transfer.tostring.autodetect");
		findSrcZip = Outside.service(this,"gus06.java.home.srczip");
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Class c = (Class) obj;
		
		File file = (File) findSrcZip.g();
		if(file==null) return null;
		
		ZipFile zipFile = null;
		String src = null;
		
		try
		{
			zipFile = (ZipFile) buildZipFile.t(file);
			src = extract(zipFile,c);
		}
		finally	{if(zipFile!=null) zipFile.close();}
		return src;
	}
	
	
	
	
	
	private String extract(ZipFile zipFile, Class c) throws Exception
	{
		String name = c.getName().replace(".","/")+".java";
		
		ZipEntry entry = zipFile.getEntry(name);
		if(entry==null) return null;
		
		InputStream is = zipFile.getInputStream(entry);
		if(is==null) return null;
		
		return (String) isToString.t(is);
	}
}