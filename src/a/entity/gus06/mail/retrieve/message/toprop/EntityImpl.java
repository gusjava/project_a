package a.entity.gus06.mail.retrieve.message.toprop;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Enumeration;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Header;

public class EntityImpl implements Entity, T {

	public static final String KEY_FROM = "from";
	public static final String KEY_TO = "to";
	public static final String KEY_CC = "cc";
	public static final String KEY_BCC = "bcc";
	public static final String KEY_SENT = "sent";
	public static final String KEY_RECEIVED = "received";
	public static final String KEY_SUBJECT = "subject";
	public static final String KEY_TEXT = "text";
	public static final String KEY_HTML = "html";
	public static final String KEY_CONTENTTYPE = "contenttype";
	public static final String KEY_UID = "uid";
	

	public String creationDate() {return "20151014";}

	
	private Service contentText;
	private Service contentHtml;
	private Service toUID;
	
	
	public EntityImpl() throws Exception
	{
		contentText = Outside.service(this,"gus06.mail.retrieve.message.content.text");
		contentHtml = Outside.service(this,"gus06.mail.retrieve.message.content.html");
		toUID = Outside.service(this,"gus06.mail.retrieve.message.uid");
	}



	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return buildProp((Message) obj);
	}

	
	private Properties buildProp(Message message) throws Exception
	{
		Properties prop = new Properties();
		
		String from = from(message);
		String to = to(message);
		String cc = cc(message);
		String bcc = bcc(message);
		String sent = dateToString(message.getSentDate());
		String received = dateToString(message.getReceivedDate());
		String contentType = message.getContentType();
		String subject = subject(message);
		String text = contentText(message);
		String html = contentHtml(message);
		String uid = uid(message);
		
		prop.setProperty(KEY_FROM,from);
		prop.setProperty(KEY_TO,to);
		prop.setProperty(KEY_CC,cc);
		prop.setProperty(KEY_BCC,bcc);
		prop.setProperty(KEY_SENT,sent);
		prop.setProperty(KEY_RECEIVED,received);
		prop.setProperty(KEY_SUBJECT,subject);
		prop.setProperty(KEY_TEXT,text);
		prop.setProperty(KEY_HTML,html);
		prop.setProperty(KEY_CONTENTTYPE,contentType);
		prop.setProperty(KEY_UID,uid);
		
		prop.setProperty("DEBUG_messageClass", message.getClass().getName());
		prop.setProperty("DEBUG_contentClass", message.getContent().getClass().getName());
		
		if(message instanceof MimeMessage)
		{
			MimeMessage mime = (MimeMessage) message;
			
			Enumeration headers = mime.getAllHeaders();
			while(headers.hasMoreElements())
			{
				Header h = (Header) headers.nextElement();
				prop.setProperty("HEADER_"+h.getName(),h.getValue());
			}
		}
		return prop;
	}
	
	
	private String subject(Message message) throws MessagingException
	{
		return formatString(message.getSubject());
	}
	
	
	private String from(Message message) throws MessagingException
	{
		String[] n = message.getHeader("From");
		if(n!=null && n.length>0) return n[0];
		
		n = message.getHeader("from");
		if(n!=null && n.length>0) return n[0];
		return "?";
	}
	
	private String to(Message message) throws MessagingException
	{
		Address[] addr = message.getRecipients(Message.RecipientType.TO);
		return formatString(InternetAddress.toString(addr));
	}
	
	private String cc(Message message) throws MessagingException
	{
		Address[] addr = message.getRecipients(Message.RecipientType.CC);
		return formatString(InternetAddress.toString(addr));
	}
	
	private String bcc(Message message) throws MessagingException
	{
		Address[] addr = message.getRecipients(Message.RecipientType.BCC);
		return formatString(InternetAddress.toString(addr));
	}
	
	private String contentText(Message message)
	{
		try{return formatString((String) contentText.t(message));}
		catch(Exception e)
		{
			Outside.err(this,"contentText(Message)",e);
			return e.toString();
		}
	}
	
	private String contentHtml(Message message)
	{
		try{return formatString((String) contentHtml.t(message));}
		catch(Exception e)
		{
			Outside.err(this,"contentHtml(Message)",e);
			return e.toString();
		}
	}
	
	private String uid(Message message)
	{
		try{return ""+toUID.t(message);}
		catch(Exception e)
		{
			Outside.err(this,"uid(Message)",e);
			return e.toString();
		}
	}
	
	
	private String formatString(String s)
	{return s==null?"":s;}
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	private String dateToString(Date date)
	{return date==null?"":sdf.format(date);}

}