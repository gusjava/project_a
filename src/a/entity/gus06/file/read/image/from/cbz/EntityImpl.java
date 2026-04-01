package a.entity.gus06.file.read.image.from.cbz;

import java.io.File;
import a.framework.*;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201204";}

	
	private Service firstFile;
	private Service readImage;
	private Service writeToFile;
	private Service buildZipFile;
	
	public EntityImpl() throws Exception
	{
		firstFile = Outside.service(this,"gus06.file.zip.findentries.firstfile");
		readImage = Outside.service(this,"gus06.convert.inputstreamtobufferedimage");
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
	}
	

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return null;
		
		String entry = (String) firstFile.t(file);
		if(entry==null) return null;
		
		ZipFile zipFile = (ZipFile) buildZipFile.t(file);
		ZipEntry zipEntry = zipFile.getEntry(entry);
		
		InputStream is = zipFile.getInputStream(zipEntry);
		Object image = readImage.t(is);
		is.close();
		zipFile.close();
		
		return image;
	}
}