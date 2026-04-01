package a.entity.gus06.file.write.inputstream;

import a.framework.*;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180321";}


	private Service transfert;
	
	public EntityImpl() throws Exception
	{
		transfert = Outside.service(this,"gus06.io.transfer");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		InputStream is = (InputStream) o[1];
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		FileOutputStream fos = new FileOutputStream(file);
		transfert.p(new Object[]{is,fos});
	}
}
