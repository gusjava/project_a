package a.entity.gus06.convert.inputstreamtofile;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150812";}


	private Service tmpFile;
	private Service transfert;
	
	public EntityImpl() throws Exception
	{
		tmpFile = Outside.service(this,"gus06.file.tmpfile");
		transfert = Outside.service(this,"gus06.io.transfer");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		File file = (File) tmpFile.g();
		
		FileOutputStream fos = new FileOutputStream(file);
		transfert.p(new Object[]{is,fos});
		return file;
	}
}
