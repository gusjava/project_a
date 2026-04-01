package a.entity.gus06.file.write.mail;

import a.framework.*;
import java.io.File;
import javax.mail.Message;
import java.io.FileOutputStream;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160609";}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Message message = (Message) o[1];
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		FileOutputStream fos = new FileOutputStream(file);
		message.writeTo(fos);
		fos.close();
	}
}
