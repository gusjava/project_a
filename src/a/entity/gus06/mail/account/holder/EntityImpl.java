package a.entity.gus06.mail.account.holder;

import a.framework.*;
import javax.mail.Store;
import javax.mail.Transport;

public class EntityImpl implements Entity, R, V, E {

	public String creationDate() {return "20160607";}


	private Service buildTransport;
	private Service buildStoreImap;
	private Service buildStorePop3;
	
	private String login;
	private String password;
	private String type;

	private Transport transport;
	private Store storeImap;
	private Store storePop3;
	
	
	public EntityImpl() throws Exception
	{
		buildTransport = Outside.service(this,"gus06.mail.transport.builder.smtp");
		buildStoreImap = Outside.service(this,"gus06.mail.store.builder.imap");
		buildStorePop3 = Outside.service(this,"gus06.mail.store.builder.pop3");
	}

	
	public void e() throws Exception
	{resetAll();}
	

	public Object r(String key) throws Exception
	{
		if(key.equals("transport")) return getTransport();
		if(key.equals("storeImap")) return getStoreImap();
		if(key.equals("storePop3")) return getStorePop3();
		
		if(key.equals("keys")) return new String[]{"transport","storeImap","storePop3"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("login")) {login = (String) obj;return;}
		if(key.equals("password")) {password = (String) obj;return;}
		if(key.equals("type")) {type = (String) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
    
    
    
	private void resetAll()
	{
		if(transport!=null) closeTransport();
		if(storeImap!=null) closeStoreImap();
		if(storePop3!=null) closeStorePop3();
    	
		transport = null;
		storeImap = null;
		storePop3 = null;
	}
	
	private void closeTransport()
	{
		if(transport==null) return;
		try{transport.close();}
		catch(Exception e){}
	}
    
	private void closeStoreImap()
	{
		if(storeImap==null) return;
		try{storeImap.close();}
		catch(Exception e){}
	}
	
	private void closeStorePop3()
	{
		if(storePop3==null) return;
		try{storePop3.close();}
		catch(Exception e){}
	}
	
	
	
	
	private void checkLoginPassword() throws Exception
	{
		if(login==null || login.equals(""))
			throw new Exception("Login has not been initialized");
		if(password==null || password.equals(""))
			throw new Exception("Password has not been initialized");
	}
	
	
	
	
	private Transport getTransport() throws Exception
	{
		if(transport==null || !transport.isConnected()) initTransport();
		return transport;
	}
	private void initTransport() throws Exception
	{
		checkLoginPassword();
		transport = (Transport) buildTransport.t(new String[]{login,password,type});
	}



	private Store getStoreImap() throws Exception
	{
		if(storeImap==null || !storeImap.isConnected()) initStoreImap();
		return storeImap;
	}
	private void initStoreImap() throws Exception
	{
		checkLoginPassword();
		storeImap = (Store) buildStoreImap.t(new String[]{login,password,type});
	}
	
	
	
	private Store getStorePop3() throws Exception
	{
		if(storePop3==null || !storePop3.isConnected()) initStorePop3();
		return storePop3;
	}
	private void initStorePop3() throws Exception
	{
		checkLoginPassword();
		storePop3 = (Store) buildStorePop3.t(new String[]{login,password,type});
	}
}