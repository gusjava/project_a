package a.entity.gus06.file.zip.readentry.asprop;

import a.framework.*;
import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Map;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231125";}
	
	
	private Service buildZipFile;
	private Service isToProp;
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
		isToProp = Outside.service(this,"gus06.io.transfer.toproperties");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		ZipFile zipFile = toZipFile(o[0]);
		if(zipFile==null) throw new Exception("ZipEntry not found: "+o[0]);
		
		ZipEntry zipEntry = toZipEntry(zipFile, o[1]);
		if(zipEntry==null) throw new Exception("ZipEntry not found: "+o[1]+" for zipFile "+zipFile);
		
		InputStream is = zipFile.getInputStream(zipEntry);
		Map prop = (Map) isToProp.t(is);
		is.close();
		zipFile.close();
		
		return prop;
	}
	
	
	private ZipFile toZipFile(Object obj) throws Exception
	{
		if(obj instanceof ZipFile) return (ZipFile) obj;
		if(obj instanceof File) return (ZipFile) buildZipFile.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private ZipEntry toZipEntry(ZipFile zipFile, Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof ZipEntry) return (ZipEntry) obj;
		if(obj instanceof String) return zipFile.getEntry((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}