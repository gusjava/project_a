package a.entity.gus06.file.zip.zipfile.entrymap;

import a.framework.*;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Map;
import java.util.HashMap;
import java.util.Enumeration;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191009";}


	public Object t(Object obj) throws Exception
	{
		ZipFile zipFile = (ZipFile) obj;
		Map map = new HashMap();

		Enumeration en = zipFile.entries();
		while(en.hasMoreElements())
		{
			ZipEntry entry = (ZipEntry) en.nextElement();
			String name = entry.getName();
			map.put(name,entry);
		}
		return map;
	}
}
