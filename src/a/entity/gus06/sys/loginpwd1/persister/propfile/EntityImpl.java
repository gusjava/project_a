package a.entity.gus06.sys.loginpwd1.persister.propfile;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201115";}
	
	public static final String KEY_LOGIN = "login";
	public static final String KEY_PASSWORD = "password";
	


	private Service accessProp;
	private Service askLoginPwd;
	private Service askPwd;
	private Service encrypt;
	private Service decrypt;

	public EntityImpl() throws Exception
	{
		accessProp = Outside.service(this,"gus06.file.properties.access.map");
		askLoginPwd = Outside.service(this,"gus06.security.askinfo.loginpassword1");
		askPwd = Outside.service(this,"gus06.security.askinfo.password1");
		encrypt = Outside.service(this,"gus06.crypto.pbe1.string.encrypt");
		decrypt = Outside.service(this,"gus06.crypto.pbe1.string.decrypt");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object access = accessProp.t(obj);
		return new Holder(access);
	}
	
	
	
	private class Holder implements R, V, E
	{
		private String login;
		private String password;
		private Object access;
		
		public Holder(Object access)
		{this.access = access;}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("login")) return getLogin();
			if(key.equals("password")) return getPassword();
			
			if(key.equals("keys")) return new String[]{"login","password"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("login")) {setLogin((String) obj);return;}
			if(key.equals("password")) {setPassword((String) obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public void e() throws Exception
		{
			initData();
		}
		
		
		
		private String getLogin() throws Exception
		{
			loadData();
			return login;
		}
		
		private String getPassword() throws Exception
		{
			loadData();
			return password;
		}
		
		private void setLogin(String login) throws Exception
		{
			this.login = login;
			saveData();
		}
		
		private void setPassword(String password) throws Exception
		{
			this.password = password;
			saveData();
		}
		
		
		
		
		private void initData() throws Exception
		{
			loadData();
			if(login==null) askLoginPwd();
			else if(password==null) askPwd();
		}
		
		private void loadData() throws Exception
		{
			Map map = (Map) ((G)access).g();
			login = get(map,KEY_LOGIN);
			password = decrypt(get(map,KEY_PASSWORD));
		}
		
		private void saveData() throws Exception
		{
			Map map = (Map) ((G)access).g();
			put(map,KEY_LOGIN,login);
			put(map,KEY_PASSWORD,encrypt(password));
			((P)access).p(map);
		}
		
		
		
		private void askLoginPwd() throws Exception
		{
			String[] infos = (String[]) askLoginPwd.g();
			
			login = infos!=null ? infos[0] : null;
			password = infos[1];
			saveData();
		}
		
		private void askPwd() throws Exception
		{
			String info = (String) askPwd.g();
			if(info==null) return;
			
			password = info;
			saveData();
		}
		
		
		private String get(Map map, String key)
		{
			return map.containsKey(key) ? (String) map.get(key) : null;
		}
		
		private void put(Map map, String key, String value)
		{
			if(value==null) map.remove(key);
			else map.put(key,value); 
		}
		
		
		private String encrypt(String s) throws Exception
		{return s!=null ? (String) encrypt.t(s) : null;}
		
		private String decrypt(String s) throws Exception
		{return s!=null ? (String) decrypt.t(s) : null;}
	}
		
}