package a.entity.gus06.file.zip.build.zipfile;

import a.framework.*;
import java.io.File;
import java.util.zip.ZipFile;
import java.nio.charset.Charset;
import java.util.zip.ZipException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191009";}
	
	public static final Charset CHARSET = Charset.forName("Cp437");
	public static final Charset CHARSET1 = Charset.forName("US-ASCII");

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		try {return new ZipFile(file,ZipFile.OPEN_READ,CHARSET); }
		catch(ZipException e)
		{throw new Exception("Failed to open zip file: "+file, e);}
	}
}
