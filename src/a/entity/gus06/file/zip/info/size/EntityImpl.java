package a.entity.gus06.file.zip.info.size;

import a.framework.*;
import java.util.*;
import java.io.File;
import java.util.zip.ZipFile;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191201";}


	private Service buildZipFile;
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		ZipFile zipFile = (ZipFile) buildZipFile.t(file);
		int size = zipFile.size();
		zipFile.close();
		return Integer.valueOf(size);
	}
}
