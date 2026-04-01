package a.entity.gus06.file.ext.infer;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180410";}


	private Service findMime;
	private Service mimeToExt;


	public EntityImpl() throws Exception
	{
		findMime = Outside.service(this,"gus06.file.mime.tika.detect.asstring");
		mimeToExt = Outside.service(this,"gus06.file.mime.tika.mimetoextension");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String mime = (String) findMime.t(file);
		return mimeToExt.t(mime);
	}
}
