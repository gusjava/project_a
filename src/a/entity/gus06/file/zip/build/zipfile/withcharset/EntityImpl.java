package a.entity.gus06.file.zip.build.zipfile.withcharset;

import a.framework.*;
import java.io.File;
import java.util.zip.ZipFile;
import java.nio.charset.Charset;
import java.util.zip.ZipException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201204";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Charset charset = (Charset) o[1];
		
		try {return new ZipFile(file,ZipFile.OPEN_READ,charset); }
		catch(ZipException e)
		{throw new Exception("Failed to open zip file: "+file, e);}
	}
}
