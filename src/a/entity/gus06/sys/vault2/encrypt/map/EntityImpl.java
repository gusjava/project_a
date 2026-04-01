package a.entity.gus06.sys.vault2.encrypt.map;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231026";}

	public static final String KEY_CRYPTED = "CRYPTED";


	private Service askInfo;
	private Service encrypt;
	private Service storePwd;

	public EntityImpl() throws Exception
	{
		askInfo = Outside.service(this,"gus06.security.askinfo.loginpassword1");
		encrypt = Outside.service(this,"gus06.crypto.pbe.object.encrypt.base64");
		storePwd = Outside.service(this,"gus06.sys.vault1.pwdstore");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String[] infos = (String[]) askInfo.g();
		if(infos==null) return null;
			
		String login = (String) infos[0];
		String pwd = (String) infos[1];
		
		T encrypter = (T) encrypt.t(pwd);
		Map mapEnc = (Map) encrypter.t(map);
		
		mapEnc.put(KEY_CRYPTED, login);
		storePwd.v(login, pwd);
		return mapEnc;
	}
}