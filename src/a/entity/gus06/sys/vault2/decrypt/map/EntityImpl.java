package a.entity.gus06.sys.vault2.decrypt.map;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import javax.crypto.BadPaddingException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231026";}


	public static final String KEY_CRYPTED = "CRYPTED";

	private Service askPwd;
	private Service decrypt;
	private Service storePwd;

	public EntityImpl() throws Exception
	{
		askPwd = Outside.service(this,"gus06.security.askinfo.password1");
		decrypt = Outside.service(this,"gus06.crypto.pbe.object.decrypt.base64");
		storePwd = Outside.service(this,"gus06.sys.vault1.pwdstore");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		if(!map.containsKey(KEY_CRYPTED)) return map;
		String login = (String) map.get(KEY_CRYPTED);
		map.remove(KEY_CRYPTED);
		
		String pwd = findPwd(login);
		if(pwd==null) throw new Exception("Unknown password for "+login);
		
		T decrypter = (T) decrypt.t(pwd);
		try
		{
			Map decrMap = (Map) decrypter.t(map);
			storePwd.v(login, pwd);
			return decrMap;
		}
		catch(BadPaddingException e)
		{
			storePwd.v(login, null);
			throw e;
		}
	}
	
	
	private String findPwd(String login) throws Exception
	{
		String pwd = (String) storePwd.r(login);
		if(pwd!=null) return pwd;
		return (String) askPwd.g();
	}
}