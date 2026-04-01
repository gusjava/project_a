package a.entity.gus06.sys.mailclient1.tool.message.retrieve.local;

import a.framework.*;
import javax.mail.Message;
import java.io.File;
import java.util.Map;
import javax.swing.Icon;
import java.util.Date;
import java.text.SimpleDateFormat;

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


	private Service readProp;
	private Service readEml;
	
	private Icon iconMailUnread;
	private Icon iconMailRead;
	private Icon iconMailReplied;
	
	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus06.file.read.properties");
		readEml = Outside.service(this,"gus06.file.read.mail");
		
		iconMailUnread = (Icon) Outside.resource(this,"icon#MAIL_mailUnread");
		iconMailRead = (Icon) Outside.resource(this,"icon#MAIL_mailRead");
		iconMailReplied = (Icon) Outside.resource(this,"icon#MAIL_mailReplied");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((File) obj);}
	
	
	private class Holder implements R, F, E, V
	{
		private File dir;
		
		private File emlFile;
		private File propFile;
		private File attachDir;
		private File doneFile;
		
		private Map data;
		
		private Icon icon;
		
		private Message message;
		
		private Date receivedDate;
		private Date sentDate;
		private String subject;
		private String type;
		
		private String from;
		private String replyTo;
		private String recipientsTO;
		private String recipientsCC;
		private String recipientsBCC;
		
		private boolean answered;
		private boolean seen;
		private boolean draft;
		private boolean flagged;
		private boolean deleted;
		private boolean out;
		
		private String contentType;
		private File source;
		
		private String msgAsText;
		private String msgAsHtml;
		
		public Holder(File dir) throws Exception
		{
			this.dir = dir;
			init();
		}
		
		private void init() throws Exception
		{
			emlFile = new File(dir, "message.eml");
			propFile = new File(dir, "message.properties");
			attachDir = new File(dir, "attachments");
			doneFile = new File(dir, "done");
			
			if(!doneFile.exists()) throw new Exception("Done file not found: "+doneFile);
			if(!emlFile.exists()) throw new Exception("Eml file not found: "+emlFile);
			if(!propFile.exists()) throw new Exception("Properties file not found: "+propFile);
			
			data = (Map) readProp.t(propFile);
			
			receivedDate = stringToDate((String) data.get(KEY_RECEIVED));
			sentDate = stringToDate((String) data.get(KEY_SENT));
			subject = (String) data.get(KEY_SUBJECT);
			msgAsText = (String) data.get(KEY_TEXT);
			msgAsHtml = (String) data.get(KEY_HTML);
			
			from = get(KEY_FROM, "");
			replyTo = get(KEY_REPLYTO, "");
			recipientsTO = get(KEY_TO, "");
			recipientsCC = get(KEY_CC, "");
			recipientsBCC = get(KEY_BCC, "");
			
			answered = Boolean.parseBoolean(get(KEY_ANSWERED, "false"));
			seen = Boolean.parseBoolean(get(KEY_SEEN, "false"));
			draft = Boolean.parseBoolean(get(KEY_DRAFT, "false"));
			flagged = Boolean.parseBoolean(get(KEY_FLAGGED, "false"));
			deleted = Boolean.parseBoolean(get(KEY_DELETED, "false"));
			out = Boolean.parseBoolean(get(KEY_OUT, "false"));
			
			type = buildType();
			icon = buildIcon();
			
			contentType = get(KEY_CONTENTTYPE, "");
		}
		
		
		private Message findMessage() throws Exception
		{
			if(message==null) initMessage();
			return message;
		}
		
		private void initMessage() throws Exception
		{
			message = (Message) readEml.t(emlFile);
		}
		
		
		private String buildType()
		{
			StringBuffer b = new StringBuffer();
			if(answered) b.append("A");
			if(seen) b.append("S");
			else  b.append("U");
			if(draft) b.append("D");
			if(flagged) b.append("F");
			if(deleted) b.append("R");
			return b.toString();
		}
		
		private Icon buildIcon() throws Exception
		{
			if(type.contains("A")) return iconMailReplied;
			if(type.contains("S")) return iconMailRead;
			if(type.contains("U")) return iconMailUnread;
			throw new Exception("Invalid mail type: "+type);
		}
		
		
		public void e() throws Exception
		{init();}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("message")) {message = (Message) obj;return;}
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("message")) return findMessage();
			if(key.equals("icon")) return icon;
			if(key.equals("receivedDate")) return receivedDate;
			if(key.equals("sentDate")) return sentDate;
			if(key.equals("subject")) return subject;
			if(key.equals("type")) return type;
			if(key.equals("from")) return from;
			if(key.equals("replyTo")) return replyTo;
			if(key.equals("recipientsTO")) return recipientsTO;
			if(key.equals("recipientsCC")) return recipientsCC;
			if(key.equals("recipientsBCC")) return recipientsBCC;
			if(key.equals("contentType")) return contentType;
			if(key.equals("msgAsText")) return msgAsText;
			if(key.equals("msgAsHtml")) return msgAsHtml;
			if(key.equals("attachments")) return attachments();
			
			if(key.equals("keys")) return new String[]{
				"message",
				"icon",
				"receivedDate",
				"sentDate",
				"subject",
				"type",
				"from",
				"replyTo",
				"recipientsTO",
				"recipientsCC",
				"recipientsBCC",
				"msgAsText",
				"msgAsHtml",
				"attachments"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		public boolean f(Object obj) throws Exception
		{
			String s = (String) obj;
			if(s.equals("answered")) return answered;
			if(s.equals("seen")) return seen;
			if(s.equals("unseen")) return !seen;
			if(s.equals("draft")) return draft;
			if(s.equals("flagged")) return flagged;
			if(s.equals("deleted")) return deleted;
			if(s.equals("out")) return out;
			
			throw new Exception("Unknown key: "+s);
		}
		
		
		private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		
		private Date stringToDate(String s) throws Exception
		{return s!=null && !s.equals("") ? sdf.parse(s) : null;}
		
		
		private String get(String key, String defaultValue)
		{
			if(!data.containsKey(key)) return defaultValue;
			return (String) data.get(key);
		}
		
		private File[] attachments()
		{
			if(!attachDir.exists()) return new File[0];
			return attachDir.listFiles();
		}
	}
}