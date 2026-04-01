package a.entity.gus06.mail.retrieve.message.extractfiles;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import a.framework.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240325";}


	private Service normalize;
	
	public EntityImpl() throws Exception
	{
		normalize = Outside.service(this,"gus06.string.transform.normalize.filename");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Message msg = (Message) o[0];
		File dir = (File) o[1];
		
		String contentType = msg.getContentType();
		if(!contentType.contains("multipart")) return;
		
		MimeMultipart content = (MimeMultipart) msg.getContent();
		int number = content.getCount();
		for(int i=0;i<number;i++)
		{
			MimeBodyPart bodyPart = (MimeBodyPart) content.getBodyPart(i);
			Object bodyContent = bodyPart.getContent();
			String disposition = bodyPart.getDisposition();
			
			if(Objects.equals(disposition, "attachment"))
			{
				String fileName = bodyPart.getFileName();
				String fileName1 = (String) normalize.t(fileName);
				File f = new File(dir, fileName1);
				dir.mkdirs();
				bodyPart.saveFile(f);
			}
		}
	}
}
