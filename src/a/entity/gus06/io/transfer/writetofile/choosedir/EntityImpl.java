package a.entity.gus06.io.transfer.writetofile.choosedir;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140818";}


	private Service nameToFile;
	private Service transfert;
	
	public EntityImpl() throws Exception
	{
		nameToFile = Outside.service(this,"gus06.file.choose.save.dir.nametofile");
		transfert = Outside.service(this,"gus06.io.transfer");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		File file = (File) nameToFile.t(key);
		if(file==null) return;
		
		file.getParentFile().mkdirs();
		FileOutputStream fos = new FileOutputStream(file);
		transfert.p(new Object[]{is,fos});
	}
}
