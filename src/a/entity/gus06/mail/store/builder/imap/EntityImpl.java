package a.entity.gus06.mail.store.builder.imap;

import a.framework.*;
import java.util.Map;
import java.util.Properties;
import javax.mail.URLName;
import javax.mail.Session;
import javax.mail.Store;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201112";}
	
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
		buildHotmail = Outside.service(this,"gus06.mail.store.builder.imap.hotmail");
		buildGmail = Outside.service(this,"gus06.mail.store.builder.imap.gmail");
		buildGandi = Outside.service(this,"gus06.mail.store.builder.imap.gandi");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return buildFromMap((Map) obj);
		if(obj instanceof String[]) return buildFromArray((String[]) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Store buildFromMap(Map map) throws Exception
	{
		String host = (String) map.get("mail.imap.host");
		String login = (String) map.get("mail.imap.user");
		String password = (String) map.get("mail.imap.pwd");
		int port = Integer.parseInt((String) map.get("mail.imap.port"));
		
		Properties p = new Properties();
		p.putAll(map);
		
		Session session = Session.getDefaultInstance(p, null);
		Store store = session.getStore("imaps");
		store.connect(host, port, login, password);
		
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