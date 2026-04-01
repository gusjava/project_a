package a.entity.gus06.file.zip.getentry.inputstream;

import a.framework.*;
import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Map;
import java.io.InputStream;
import javax.swing.ImageIcon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231126";}
	
	
	private Service buildZipFile;
	private Service isToByteArray;
	private Service wrapInputStream;
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
		isToByteArray = Outside.service(this,"gus06.io.transfer.tobytearray");
		wrapInputStream = Outside.service(this,"gus06.io.inputstream.wrap.withcloseable");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		ZipFile zipFile = toZipFile(o[0]);
		String entryName = (String) o[1];
		
		ZipEntry zipEntry = zipFile.getEntry(entryName);
		if(zipEntry==null) throw new Exception("ZipEntry not found: "+entryName+" for zipFile "+zipFile);
		
		InputStream is = (InputStream) zipFile.getInputStream(zipEntry);
		return wrapInputStream.t(new Object[]{is,zipFile});
	}
	
	
	private ZipFile toZipFile(Object obj) throws Exception
	{
		if(obj instanceof ZipFile) return (ZipFile) obj;
		if(obj instanceof File) return (ZipFile) buildZipFile.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
