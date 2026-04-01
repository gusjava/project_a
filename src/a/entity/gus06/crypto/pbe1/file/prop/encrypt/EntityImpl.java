package a.entity.gus06.crypto.pbe1.file.prop.encrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150625";}


	private Service encrypt;
	private Service read;

	public EntityImpl() throws Exception
	{
		encrypt = Outside.service(this,"gus06.crypto.pbe1.object.encrypt");
		read = Outside.service(this,"gus06.file.read.properties");
	}



	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		Properties p = (Properties) read.t(file);
		
		Map m = (Map) encrypt.t(p);
		p = new Properties();
		p.putAll(m);
		
		FileOutputStream fos = new FileOutputStream(file);
		p.store(fos,"");
		fos.close();
	}
}
