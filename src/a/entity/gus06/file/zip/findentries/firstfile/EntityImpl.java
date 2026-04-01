package a.entity.gus06.file.zip.findentries.firstfile;

import a.framework.*;
import java.util.*;
import java.io.File;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201204";}


	private Service findEntries;
	
	public EntityImpl() throws Exception
	{
		findEntries = Outside.service(this,"gus06.file.zip.findentries.onlyfiles");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		List list = (List) findEntries.t(file);
		if(list.isEmpty()) return null;
		return list.get(0);
	}
}