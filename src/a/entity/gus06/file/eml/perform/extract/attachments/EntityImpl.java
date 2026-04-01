package a.entity.gus06.file.eml.perform.extract.attachments;

import a.framework.*;
import java.io.File;
import java.util.Set;
import javax.mail.Message;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240320";}
	
	public static final int BUFFER = 2048;

	
	private Service readEml;
	
	public EntityImpl() throws Exception
	{
		readEml = Outside.service(this,"gus06.file.read.mail");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		File dir = (File) o[1];
		Object progress = o[2];
		Set interrupt = (Set) o[3];
		
		Message msg = (Message) readEml.t(file);
		
		String contentType = msg.getContentType();
		if(!contentType.contains("multipart")) return;
		
		MimeMultipart content = (MimeMultipart) msg.getContent();
		int number = content.getCount();
		
		List list = new ArrayList();
		for(int i=0;i<number;i++)
		{
			MimeBodyPart part = (MimeBodyPart) content.getBodyPart(i);
			String disposition = part.getDisposition();
			if(Objects.equals(disposition, "attachment"))
			list.add(part);
		}
		
		if(progress!=null) ((V)progress).v("size",""+list.size());
		for(int i=0; i<list.size(); i++)
		{
			MimeBodyPart part = (MimeBodyPart) list.get(i);
			String fileName = part.getFileName();
			File f = new File(dir, fileName);
			part.saveFile(f);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}
