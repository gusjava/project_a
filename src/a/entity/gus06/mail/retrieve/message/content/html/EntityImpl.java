package a.entity.gus06.mail.retrieve.message.content.html;


import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.BodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage;
import a.framework.*;
import java.util.Objects;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240322";}


	public Object t(Object obj) throws Exception
	{
		Message msg = (Message) obj;
		if(msg==null) return null;
		
		Object content = msg.getContent();
		String type = msg.getContentType();
		return extractFrom(content, type);
	}
	
	private String extractFrom(Object content, String type) throws Exception
	{
		if(type.startsWith("text/html")) return (String) content;
		if(type.startsWith("multipart/")) return handleMultipart((MimeMultipart) content);
		return null;
	}
	
	private String handleMultipart(MimeMultipart content) throws Exception
	{
		int number = content.getCount();
		for(int i=0;i<number;i++)
		{
			BodyPart bodyPart = content.getBodyPart(i);
			String disposition = bodyPart.getDisposition();
			
			if(!Objects.equals(disposition, "attachment"))
			{
				Object bodyContent = bodyPart.getContent();
				String bodyType = bodyPart.getContentType();
				String text = extractFrom(bodyContent, bodyType);
				if(text!=null) return text;
			}
		}
		return null;
	}
}