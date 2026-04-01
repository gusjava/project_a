package a.entity.gus06.file.zip.getentry.url;

import a.framework.*;
import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Map;
import java.io.InputStream;
import javax.swing.ImageIcon;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250504";}
	
	
	private Service buildZipFile;
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		ZipFile zipFile = toZipFile(o[0]);
		String entryName = (String) o[1];
		
		ZipEntry zipEntry = zipFile.getEntry(entryName);
		if(zipEntry==null) throw new Exception("ZipEntry not found: "+entryName+" for zipFile "+zipFile);
		
		return buildURL(zipFile, zipEntry);
	}
	
	
	private ZipFile toZipFile(Object obj) throws Exception
	{
		if(obj instanceof ZipFile) return (ZipFile) obj;
		if(obj instanceof File) return (ZipFile) buildZipFile.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private URL buildURL(ZipFile zipFile, ZipEntry zipEntry) throws Exception
	{
		File file = new File(zipFile.getName());
		String path = file.getAbsolutePath().replace("\\", "/");
		return new URL("jar:file:" + path + "!/" + zipEntry.getName());
	}
}