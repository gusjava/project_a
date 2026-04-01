package a.entity.gus06.file.zip.findentries.asstring;

import a.framework.*;
import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190305";}
	
	
	private Service buildZipFile;
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		ZipFile zipFile = (ZipFile) buildZipFile.t(file);

		StringBuilder b = new StringBuilder();
		Enumeration en = zipFile.entries();
		while(en.hasMoreElements())
		{
			ZipEntry entry = (ZipEntry) en.nextElement();
			b.append(entry.getName());
			b.append("\n");
		}
		zipFile.close();
		return b.toString();
	}
}
