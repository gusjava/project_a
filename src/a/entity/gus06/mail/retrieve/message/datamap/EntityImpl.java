package a.entity.gus06.mail.retrieve.message.datamap;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Enumeration;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Folder;
import javax.mail.Flags;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240325";}

	public static final String KEY_FROM = "from";
	public static final String KEY_REPLYTO = "replyto";
	public static final String KEY_TO = "to";
	public static final String KEY_CC = "cc";
	public static final String KEY_BCC = "bcc";
	
	public static final String KEY_SENT = "sent";
	public static final String KEY_RECEIVED = "received";
	
	public static final String KEY_SUBJECT = "subject";
	public static final String KEY_TEXT = "text";
	public static final String KEY_HTML = "html";
	
	public static final String KEY_UID = "uid";
	public static final String KEY_CONTENTTYPE = "contentype";
	public static final String KEY_FOLDER = "folder";
	public static final String KEY_OUT = "out";
	
	public static final String KEY_ANSWERED = "answered";
	public static final String KEY_SEEN = "seen";
	public static final String KEY_DRAFT = "draft";
	public static final String KEY_FLAGGED = "flagged";
	public static final String KEY_DELETED = "deleted";
	

	private Service contentText;
	private Service contentHtml;
	private Service toUID;
	private Service isOut;
	
	
	public EntityImpl() throws Exception
	{
		contentText = Outside.service(this,"gus06.mail.retrieve.message.content.text");
		contentHtml = Outside.service(this,"gus06.mail.retrieve.message.content.html");
		isOut = Outside.service(this,"gus06.sys.mailclient1.tool.folder.isout");
		toUID = Outside.service(this,"gus06.mail.retrieve.message.uid");
	}



	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return buildMap((Message) obj);
	}

	
	private Map buildMap(Message message) throws Exception
	{
		Map data = new HashMap();
		
		// ADDRESS
		
		Address[] from = message.getFrom();
		Address[] replyTo = message.getReplyTo();
		Address[] recipientsTO = message.getRecipients(Message.RecipientType.TO);
		Address[] recipientsCC = message.getRecipients(Message.RecipientType.CC);
		Address[] recipientsBCC = message.getRecipients(Message.RecipientType.BCC);
		
		data.put(KEY_FROM,formatAddressArray(from));
		data.put(KEY_REPLYTO,formatAddressArray(replyTo));
		data.put(KEY_TO,formatAddressArray(recipientsTO));
		data.put(KEY_CC,formatAddressArray(recipientsCC));
		data.put(KEY_BCC,formatAddressArray(recipientsBCC));
		
		// DATE
		
		Date dateSent = message.getSentDate();
		Date dateReceived = message.getReceivedDate();
		
		data.put(KEY_SENT,dateToString(dateSent));
		data.put(KEY_RECEIVED,dateToString(dateReceived));
		
		// TEXT
		
		String subject = message.getSubject();
		String text = contentText(message);
		String html = contentHtml(message);
		
		data.put(KEY_SUBJECT,formatString(subject));
		data.put(KEY_TEXT,formatString(text));
		data.put(KEY_HTML,formatString(html));
		
		// TECHNIC
		
		String uid = uid(message);
		String contentType = message.getContentType();
		Folder folder = message.getFolder();
		boolean out = isOut.f(folder);
		
		data.put(KEY_UID,uid);
		data.put(KEY_CONTENTTYPE,contentType);
		data.put(KEY_FOLDER,folder.getName());
		data.put(KEY_OUT,""+out);
		
		// FLAGS
		
		boolean answered = message.isSet(Flags.Flag.ANSWERED);
		boolean seen = message.isSet(Flags.Flag.SEEN);
		boolean draft = message.isSet(Flags.Flag.DRAFT);
		boolean flagged = message.isSet(Flags.Flag.FLAGGED);
		boolean deleted = message.isSet(Flags.Flag.DELETED);
		
		data.put(KEY_ANSWERED,""+answered);
		data.put(KEY_SEEN,""+seen);
		data.put(KEY_DRAFT,""+draft);
		data.put(KEY_FLAGGED,""+flagged);
		data.put(KEY_DELETED,""+deleted);
		
		return data;
	}
	
	
	
	private String formatString(String s)
	{
		return s==null?"":s;
	}
	
	private String formatAddressArray(Address[] d) throws MessagingException
	{
		String s = InternetAddress.toString(d);
		return s==null?"":s;
	}
	
	private String contentText(Message mail)
	{
		try{return (String) contentText.t(mail);}
		catch(Exception e)
		{
			Outside.err(this,"contentText(Message)",e);
			return e.toString();
		}
	}
	
	private String contentHtml(Message mail)
	{
		try{return (String) contentHtml.t(mail);}
		catch(Exception e)
		{
			Outside.err(this,"contentHtml(Message)",e);
			return e.toString();
		}
	}
	
	private String uid(Message mail)
	{
		try{return ""+toUID.t(mail);}
		catch(Exception e)
		{
			Outside.err(this,"uid(Message)",e);
			return e.toString();
		}
	}
	
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	private String dateToString(Date date)
	{return date==null?"":sdf.format(date);}

}