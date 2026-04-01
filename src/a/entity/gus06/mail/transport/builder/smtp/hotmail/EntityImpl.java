package a.entity.gus06.mail.transport.builder.smtp.hotmail;

import a.framework.*;
import java.util.Properties;
import com.sun.mail.pop3.POP3SSLStore;
import com.sun.mail.util.MailSSLSocketFactory;
import javax.mail.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201112";}


	public static final String SMTP_HOST = "smtp-mail.outlook.com";
	public static final int SMTP_PORT = 587;
	
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String login = o[0];
		String password = o[1];
		
		Properties p = new Properties();
		p.put("mail.smtp.host", SMTP_HOST);
		p.put("mail.smtp.user", login);
		p.put("mail.smtp.port", ""+SMTP_PORT);
		p.put("mail.smtp.starttls.enable","true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		
		Session session = Session.getDefaultInstance(p, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(login,password);
			}
		});
		
		Transport transport = session.getTransport("smtp");
		transport.connect(SMTP_HOST,login,password);
		
		return transport;
	}
}