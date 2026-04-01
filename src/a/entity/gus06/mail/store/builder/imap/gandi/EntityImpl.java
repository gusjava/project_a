package a.entity.gus06.mail.store.builder.imap.gandi;

import a.framework.*;
import java.util.Properties;
import javax.mail.URLName;
import javax.mail.Session;
import javax.mail.Store;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240313";}

	public static final String IMAP_HOST = "mail.gandi.net";
	public static final int IMAP_PORT = 993;
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String login = o[0];
		String password = o[1];
		
		Properties p = new Properties();
		
		p.put("mail.imap.ssl.trust", "*");
		p.put("mail.imap.ssl.enable", "true");
		
		p.put("mail.imaps.ssl.trust", "*");
		p.put("mail.imaps.ssl.enable", "true");
		
		Session session = Session.getInstance(p,null);
		Store store = session.getStore("imaps");
		store.connect(IMAP_HOST, IMAP_PORT, login, password);
		
		return store;
	}
}