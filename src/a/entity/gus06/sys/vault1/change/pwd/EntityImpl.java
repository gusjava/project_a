package a.entity.gus06.sys.vault1.change.pwd;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200416";}


	private Service encrypt;
	private Service writeProp;
	
	public EntityImpl() throws Exception
	{
		encrypt = Outside.service(this,"gus06.crypto.pbe.object.encrypt.base64");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File vaultFile = (File) o[0];
		Map map = (Map) o[1];
		String pwd = (String) o[2];
		
		T encrypter = (T) encrypt.t(pwd);
		Map mapEnc = (Map) encrypter.t(map);
		
		writeProp.p(new Object[]{vaultFile,mapEnc});
	}
}
