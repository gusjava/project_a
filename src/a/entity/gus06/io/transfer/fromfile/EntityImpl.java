package a.entity.gus06.io.transfer.fromfile;

import a.framework.*;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180321";}


	private Service tmpFile;
	private Service transfert;
	
	public EntityImpl() throws Exception
	{
		tmpFile = Outside.service(this,"gus06.file.tmpfile");
		transfert = Outside.service(this,"gus06.io.transfer");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		OutputStream os = (OutputStream) o[1];
		
		FileInputStream fis = new FileInputStream(file);
		transfert.p(new Object[]{fis,os});
	}
}
