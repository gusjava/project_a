package a.entity.gus06.mail.store.builder.pop3;

import a.framework.*;
import java.util.Map;
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
	
	public static final String TYPE_HOTMAIL = "HOTMAIL";
	public static final String TYPE_GMAIL = "GMAIL";
	public static final String TYPE_GANDI = "GANDI";


	private Service addressType;
	private Service buildHotmail;
	private Service buildGmail;
	private Service buildGandi;


	public EntityImpl() throws Exception
	{
		addressType = Outside.service(this,"gus06.mail.address.findtype");
		buildHotmail = Outside.service(this,"gus06.mail.store.builder.pop3.hotmail");
		buildGmail = Outside.service(this,"gus06.mail.store.builder.pop3.gmail");
		buildGandi = Outside.service(this,"gus06.mail.store.builder.pop3.gandi");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return buildFromMap((Map) obj);
		if(obj instanceof String[]) return buildFromArray((String[]) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Store buildFromMap(Map map) throws Exception
	{
		// A REVOIR ...
		
		String host = (String) map.get("mail.pop3.host");
		String login = (String) map.get("mail.pop3.user");
		String password = (String) map.get("mail.pop3.pwd");
		int port = Integer.parseInt((String) map.get("mail.pop3.port"));
		
		Properties p = new Properties();
		p.putAll(map);
		
		URLName url = new URLName("pop3",host,port,"",login,password);
		
		Session session = Session.getDefaultInstance(p, null);
		Store store = new POP3SSLStore(session,url);
		store.connect();
		
		return store;
	}
	
	private Store buildFromArray(String[] o) throws Exception
	{
		if(o.length==2) 
		{
			String login = o[0];
			String password = o[1];
			
			Service builder = findBuilder(null, login);
			return (Store) builder.t(new String[]{login, password});
		}
		if(o.length==3) 
		{
			String login = o[0];
			String password = o[1];
			String type = o[2];
			
			Service builder = findBuilder(type, login);
			return (Store) builder.t(new String[]{login, password});
		}
		throw new Exception("Wrong data number: "+o.length);
	}
	
	private Service findBuilder(String type, String login) throws Exception
	{
		if(type==null) type = (String) addressType.t(login);
		if(type.equals(TYPE_HOTMAIL)) return buildHotmail;
		if(type.equals(TYPE_GMAIL)) return buildGmail;
		if(type.equals(TYPE_GANDI)) return buildGandi;
		
		throw new Exception("Unsupported email type: "+type);
	}
}