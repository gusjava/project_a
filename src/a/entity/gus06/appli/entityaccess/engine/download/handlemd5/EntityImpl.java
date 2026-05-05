package a.entity.gus06.appli.entityaccess.engine.download.handlemd5;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150302";}


	private Service sender;
	private Service dirToSrc;
	private Service rebuildSrc;
	private Service buildMd5;
	
	public EntityImpl() throws Exception
	{
		sender = Outside.service(this,"gus06.appli.entityaccess.api.sender");
		dirToSrc = Outside.service(this,"gus06.entitydev.dirtosrc.full");
		rebuildSrc = Outside.service(this,"gus06.appli.entityaccess.engine.download.rebuildsrc");
		buildMd5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String md5 = (String) obj;
		String info = "retrieving "+md5;
		
		try
		{
			Map map = new HashMap();
			map.put("action","retrieve:src");
			map.put("md5",md5);
		
			String src = (String) sender.t(map);
			if(src.startsWith("error:")) throw new Exception("Server "+src);
			
			String md5_ = (String) buildMd5.t(src);
			if(!md5.equals(md5_)) throw new Exception("Corrupted data was received (new MD5="+md5_+")");
		
			String entityName = (String) rebuildSrc.t(src);
			return info+"\n"+entityName;
		}
		catch(Exception e)
		{
			String m1 = "Download failed for "+md5;
			String m2 = "Exception: "+e.getMessage();
			return info+"\n"+m1+"\n"+m2;
		}
	}
}
