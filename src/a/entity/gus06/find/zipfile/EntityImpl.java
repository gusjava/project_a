package a.entity.gus06.find.zipfile;

import a.framework.*;
import java.nio.charset.Charset;
import java.util.zip.ZipFile;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201204";}

	private Service buildZipFile;

	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof ZipFile) return obj;
		if(obj instanceof File) return buildZipFile.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
