package a.entity.gus06.mail.store.builder.imap.gmail;

import a.framework.*;
import java.util.Properties;
import javax.mail.URLName;
import javax.mail.Session;
import javax.mail.Store;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201112";}

	public static final String IMAP_HOST = "imap.googlemail.com";
	public static final int IMAP_PORT = -1;
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String login = o[0];
		String password = o[1];
		
		Properties p = new Properties();
		
		p.put("mail.imap.ssl.enable", "true");
		p.put("mail.imap.auth.mechanisms", "XOAUTH2");
		
		p.put("mail.imap.sasl.enable", "true");
		p.put("mail.imap.sasl.mechanisms", "XOAUTH2");
		
		p.put("mail.imap.auth.login.disable", "true");
		p.put("mail.imap.auth.plain.disable", "true");
		
		Session session = Session.getInstance(p,null);
		Store store = session.getStore("imaps");
		store.connect(IMAP_HOST, IMAP_PORT, login, password);
		
		return store;
	}
}