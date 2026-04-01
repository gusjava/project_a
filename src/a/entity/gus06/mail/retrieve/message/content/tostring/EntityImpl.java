package a.entity.gus06.mail.retrieve.message.content.tostring;

import java.io.ByteArrayInputStream;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.SharedByteArrayInputStream;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.QPDecoderStream;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151015";}

	public EntityImpl() throws Exception
	{
	}

	public Object t(Object obj) throws Exception
	{return content((Message)obj);}

	private String content(Message msg)
	{
		try
		{
			Object content = msg.getContent();
			String type = msg.getContentType();
			
			StringBuffer b = new StringBuffer();
			handle(b,content,type);
			return b.toString();
		}
		catch(Exception e)
		{
			Outside.err(this,"content(Message)",e);
			return e.toString();
		}
	}
	
	
	private void handle(StringBuffer b, Object c, String type) throws Exception
	{
		if(c==null)return;
		
		if(c instanceof String)
		{
			String s = (String)c;
			b.append(s+"\n");
			return;
		}
		if(c instanceof Multipart)
		{
			handleMultipart(b,(Multipart)c,type);
			return;
		}
		if(c instanceof MimeMessage)
		{
			MimeMessage mm = (MimeMessage)c;
			b.append("MIMEMESSAGE content-type = "+mm.getContentType()+"\n\n");
			return;
		}
		if(c instanceof SharedByteArrayInputStream)
		{
			ByteArrayInputStream bais = (ByteArrayInputStream) c;
			b.append("<ByteArrayInputStream: not supported yet><type="+type+">\n");
			return;
		}
		if(c instanceof BASE64DecoderStream)
		{
			BASE64DecoderStream decoder = (BASE64DecoderStream) c;
			b.append("<BASE64DecoderStream: not supported yet><type="+type+">\n");
			return;
		}
		if(c instanceof QPDecoderStream)
		{
			QPDecoderStream decoder = (QPDecoderStream) c;
			b.append("<QPDecoderStream: not supported yet><type="+type+">\n");
			return;
		}
		throw new Exception("Unknown content type: "+c.getClass().getName());
	}
	
	
	
	
	private void handleMultipart(StringBuffer b, Multipart m, String type)
	{
		try
		{
			int number = m.getCount();
			for(int i=0;i<number;i++)
			{
				BodyPart bodyPart = m.getBodyPart(i);
				Object c = bodyPart.getContent();
				handle(b,c,type);
			}
		}
		catch(Exception e)
		{
			Outside.err(this,"handleMultipart(StringBuffer,Multipart,String)",e);
			b.append("MULTIPART ERROR: "+e);
		}
	}
}