package a.entity.gus06.app.init.dll.extrator;

import a.framework.*;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20150607";}


	private Service inside;
	private Service transfert;
	private Service replaceArch;


	public EntityImpl() throws Exception
	{
		inside = Outside.service(this,"inside");
		transfert = Outside.service(this,"gus06.io.transfer");
		replaceArch = Outside.service(this,"gus06.string.transform.replace.tag.arch");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		File file = (File) obj;
		key = (String) replaceArch.t(key);
		
		InputStream is = (InputStream) inside.r("stream."+key);
		if(is==null) throw new Exception("resource not found: "+key);
		
		file.getParentFile().mkdirs();
		FileOutputStream fos = new FileOutputStream(file);
		transfert.p(new Object[]{is,fos});
	}
}
