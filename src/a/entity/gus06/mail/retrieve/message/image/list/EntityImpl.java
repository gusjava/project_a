package a.entity.gus06.mail.retrieve.message.image.list;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.BodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.BASE64DecoderStream;
import a.framework.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240323";}

	
	public static final String MULTIPART_ALTERNATIVE = "multipart/alternative";
	public static final String MULTIPART_MIXED = "multipart/mixed";
	public static final String MULTIPART_RELATED = "multipart/related";
	public static final String TEXT_HTML = "text/html";
	public static final String TEXT_PLAIN = "text/plain";
	public static final String IMAGE_PNG = "image/png";
	public static final String IMAGE_JPEG = "image/jpeg";


	private Service streamToImage;
	
	public EntityImpl() throws Exception
	{
		streamToImage = Outside.service(this,"gus06.mail.base64decoderstream.image");
	}


	public Object t(Object obj) throws Exception
	{
		Message msg = (Message) obj;
		if(msg==null) return null;
		
		Object content = msg.getContent();
		String type = msg.getContentType();
		
		List output = new ArrayList();
		handleContent(output, content, type);
		return output;
	}
	
	private void handleContent(List output, Object content, String type) throws Exception
	{
		String name = type.split(";")[0].trim().toLowerCase();
	
		if(name.equals(TEXT_HTML)) {}
		else if(name.equals(TEXT_PLAIN)) {}
		else if(name.equals(IMAGE_PNG)) extractImage(output, (BASE64DecoderStream) content, "png");
		else if(name.equals(IMAGE_JPEG)) extractImage(output, (BASE64DecoderStream) content, "jpeg");
		else if(name.equals(MULTIPART_ALTERNATIVE)) handleMultipart(output, (MimeMultipart) content);
		else if(name.equals(MULTIPART_RELATED)) handleMultipart(output, (MimeMultipart) content);
		else if(name.equals(MULTIPART_MIXED)) handleMultipart(output, (MimeMultipart) content);
		else throw new Exception("Unsupported contentType: "+type);
	}
	
	private void handleMultipart(List output, MimeMultipart content) throws Exception
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
				handleContent(output, bodyContent, bodyType);
			}
		}
	}
	
	private void extractImage(List output, BASE64DecoderStream content, String ext) throws Exception
	{
		Object image = streamToImage.t(new Object[]{content, ext});
		output.add(image);
	}
}