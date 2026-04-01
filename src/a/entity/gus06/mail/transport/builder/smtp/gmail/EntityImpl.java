package a.entity.gus06.mail.transport.builder.smtp.gmail;

import com.sun.mail.pop3.POP3SSLStore;
import a.framework.*;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201112";}
	
	
	public static final String SMTP_HOST = "smtp.gmail.com";
	public static final int SMTP_PORT = 465;
	
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String login = o[0];
		String password = o[1];
		
		Properties p = new Properties();
		
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.auth.mechanisms", "XOAUTH2");
		
		p.put("mail.smtp.sasl.enable", "true");
		p.put("mail.smtp.sasl.mechanisms", "XOAUTH2");
		
		p.put("mail.smtp.auth.login.disable", "true");
		p.put("mail.smtp.auth.plain.disable", "true");
		
//		p.put("mail.smtp.host", SMTP_HOST);
//		p.put("mail.smtp.user", login);
//		p.put("mail.smtp.port", ""+SMTP_PORT);
//		p.put("mail.smtp.starttls.enable","true");
//		p.put("mail.smtp.auth", "true");
//		p.put("mail.smtp.debug", "true");
//		p.put("mail.smtp.socketFactory.port", ""+SMTP_PORT);
//		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		p.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(p, null);
		Transport transport = session.getTransport("smtp");
		transport.connect(SMTP_HOST,login,password);
		
		return transport;
	}
}