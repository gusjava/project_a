package a.entity.gus06.mail.store.builder.pop3.gandi;

import a.framework.*;
import java.util.Properties;
import javax.mail.URLName;
import javax.mail.Session;
import javax.mail.Store;
import com.sun.mail.pop3.POP3SSLStore;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240313";}

	public static final String POP_HOST = "mail.gandi.net";
	public static final int POP_PORT = 995;
	
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String login = o[0];
		String password = o[1];
		
		Properties p = new Properties();
		p.setProperty("mail.pop3.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		p.setProperty("mail.pop3.socketFactory.fallback","false");
		p.setProperty("mail.pop3.port",""+POP_PORT);
		p.setProperty("mail.pop3.socketFactory.port",""+POP_PORT);
		
		URLName url = new URLName("pop3",POP_HOST,POP_PORT,"",login, password);
		
		Session session = Session.getInstance(p,null);
		Store store = new POP3SSLStore(session,url);
		store.connect();
		
		return store;
	}
}