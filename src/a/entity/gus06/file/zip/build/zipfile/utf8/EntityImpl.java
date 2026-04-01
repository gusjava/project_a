package a.entity.gus06.file.zip.build.zipfile.utf8;

import a.framework.*;
import java.io.File;
import java.util.zip.ZipFile;
import java.nio.charset.Charset;
import java.util.zip.ZipException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201204";}
	
	public static final Charset CHARSET = Charset.forName("UTF8");


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		try {return new ZipFile(file,ZipFile.OPEN_READ,CHARSET); }
		catch(ZipException e)
		{throw new Exception("Failed to open zip file: "+file, e);}
	}
}
