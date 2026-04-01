package a.entity.gus06.sys.vault1.build.map;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200416";}


	private Service decrypt;
	private Service encrypt;
	private Service fileAccess;
	private Service mapBuilder;
	private Service delayHandler;

	public EntityImpl() throws Exception
	{
		decrypt = Outside.service(this,"gus06.crypto.pbe.object.decrypt.base64");
		encrypt = Outside.service(this,"gus06.crypto.pbe.object.encrypt.base64");
		fileAccess = Outside.service(this,"gus06.file.access.properties");
		mapBuilder = Outside.service(this,"gus06.map.wrap.changedhandler");
		delayHandler = Outside.service(this,"gus06.time.handle.delayed.ms600");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File vaultFile = (File) o[0];
		String pwd = (String) o[1];
		
		Object access = fileAccess.t(vaultFile);
		
		Saver saver = new Saver((P) access,pwd);
		P delayedSaver = (P) delayHandler.t(saver);
		
		Map map = loadMap((G) access,pwd);
		return mapBuilder.t(new Object[]{map,delayedSaver});
	}
	
	
	
	
	private Map loadMap(G accessG, String pwd) throws Exception
	{
		Map rawMap = (Map) accessG.g();
		if(rawMap==null) return new HashMap();
		
		T decrypter = (T) decrypt.t(pwd);
		return (Map) decrypter.t(rawMap);
	}
	
	
	
	private class Saver implements P
	{
		private P accessP;
		private T encrypter;
		
		public Saver(P accessP, String pwd) throws Exception
		{
			this.accessP = accessP;
			this.encrypter = (T) encrypt.t(pwd);
		}
		
		public void p(Object obj) throws Exception
		{
			Map m = (Map) obj;
			accessP.p(encrypter.t(m));
		}
	}
}
