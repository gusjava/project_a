package a.entity.gus06.mail.store.builder.pop3.hotmail;

import a.framework.*;
import java.util.Properties;
import javax.mail.URLName;
import javax.mail.Session;
import javax.mail.Store;
import com.sun.mail.pop3.POP3SSLStore;
import com.sun.mail.util.MailSSLSocketFactory;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160608";}

	public static final String POP_HOST = "pop3.live.com";
	public static final int POP_PORT = 995;
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String login = o[0];
		String password = o[1];
		
		MailSSLSocketFactory socketFactory = new MailSSLSocketFactory();
		socketFactory.setTrustAllHosts(true);
		
		Properties p = new Properties();
		p.setProperty("mail.pop3s.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.setProperty("mail.pop3s.socketFactory.fallback","false");
		p.setProperty("mail.pop3s.port",""+POP_PORT);
		p.setProperty("mail.pop3s.socketFactory.port",""+POP_PORT);
		p.put("mail.pop3s.ssl.socketFactory", socketFactory);
		
		URLName url = new URLName("pop3s",POP_HOST,POP_PORT,"",login, password);
		
		Session session = Session.getInstance(p, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(login,password);
			}
		});
		
		Store store = new POP3SSLStore(session,url);
		store.connect();
		
		return store;
	}
}