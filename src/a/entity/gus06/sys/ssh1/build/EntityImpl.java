package a.entity.gus06.sys.ssh1.build;

import a.framework.*;
import java.util.Map;
import com.jcraft.jsch.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180401";}
	
	public static final String KEY_USER = "user";
	public static final String KEY_PWD = "pwd";
	public static final String KEY_PASSPHRASE = "passphrase";
	public static final String KEY_HOST = "host";
	public static final String KEY_PORT = "port";
	public static final String KEY_AUTH = "auth";
	public static final String KEY_OUTPUT = "output";
	public static final String KEY_INPUT = "input";
	
	public static final String KEY_PROMPT_PWD = "prompt_pwd";
	public static final String KEY_PROMPT_PASSPHRASE = "prompt_passphrase";
	public static final String KEY_PROMPT_YESNO = "prompt_yesno";
	public static final String KEY_SHOW_MESSAGE = "show_message";
	
	public static final int DEFAULT_PORT = 22;
	public static final String DEFAULT_USER = "root";


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		InputStream is = (InputStream) get1(map,KEY_INPUT);
		OutputStream os = (OutputStream) get1(map,KEY_OUTPUT);
		String host = (String) get1(map,KEY_HOST);
		
		String user = toString(get(map,KEY_USER),DEFAULT_USER);
		int port = toInt(get(map,KEY_PORT),DEFAULT_PORT);
		
		if(host.contains("@"))
		{
			String[] n = host.split("@");
			user = n[0];
			host = n[1];
		}
		if(host.contains(":"))
		{
			String[] n = host.split(":");
			host = n[0];
			port = toInt(n[1],port);
		}
		
		
		File authFile = toFile(get(map,KEY_AUTH));
		String pwd = (String) get(map,KEY_PWD);
		
		
		JSch jsch = new JSch();
		if(authFile!=null) jsch.addIdentity(authFile.getAbsolutePath());
		
		Session session = jsch.getSession(user, host, port);
		if(pwd!=null) session.setPassword(pwd);
		session.setUserInfo(new UserInfo1(map));
		session.connect();

		Channel channel = session.openChannel("shell");
		channel.setInputStream(is);
		channel.setOutputStream(os);
		channel.connect();
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	private int toInt(Object obj, int defaultValue)
	{
		if(obj==null) return defaultValue;
		return Integer.parseInt(""+obj);
	}
	
	private String toString(Object obj, String defaultValue)
	{
		if(obj==null) return defaultValue;
		return (String) obj;
	}
	
	private File toFile(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return new File((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class UserInfo1 implements UserInfo
	{
		private Map map;
		public UserInfo1(Map map) {this.map = map;}
		
		public String getPassphrase()
		{
			return (String) get(map,KEY_PASSPHRASE);
		}
		
		public String getPassword()
		{
			return (String) get(map,KEY_PWD);
		}
		
		public boolean promptPassword(String message)
		{
			F f = (F) get(map,KEY_PROMPT_PASSPHRASE);
			if(f==null) return false;
			
			try{return f.f(message);}
			catch(Exception e){return false;}
		}
		
		public boolean promptPassphrase(String message)
		{
			F f = (F) get(map,KEY_PROMPT_PWD);
			if(f==null) return false;
			
			try{return f.f(message);}
			catch(Exception e){return false;}
		}
		
		public boolean promptYesNo(String message)
		{
			F f = (F) get(map,KEY_PROMPT_YESNO);
			if(f==null) return false;
			
			try{return f.f(message);}
			catch(Exception e){return false;}
		}
		
		public void showMessage(String message)
		{
			P p = (P) get(map,KEY_SHOW_MESSAGE);
			if(p==null) return;
			
			try{p.p(message);}
			catch(Exception e){}
		}
	}
}
