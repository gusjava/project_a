package a.entity.gus06.file.zip.findentries.withcharset;

import a.framework.*;
import java.util.*;
import java.io.File;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201204";}


	private Service findZipFile;
	
	public EntityImpl() throws Exception
	{
		findZipFile = Outside.service(this,"gus06.file.zip.build.zipfile.withcharset");
	}

	public Object t(Object obj) throws Exception
	{
		ZipFile zipFile = (ZipFile) findZipFile.t(obj);

		ArrayList list = new ArrayList();
		Enumeration en = zipFile.entries();
		while(en.hasMoreElements())
		{
			ZipEntry entry = (ZipEntry) en.nextElement();
			list.add(entry.getName());
		}
		zipFile.close();
		
		Collections.sort(list);
		return list;
	}
}