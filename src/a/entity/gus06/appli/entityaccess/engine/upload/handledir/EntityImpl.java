package a.entity.gus06.appli.entityaccess.engine.upload.handledir;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150301";}


	private Service sender;
	private Service dirToSrc;
	
	public EntityImpl() throws Exception
	{
		sender = Outside.service(this,"gus06.appli.entityaccess.api.sender");
		dirToSrc = Outside.service(this,"gus06.entitydev.dirtosrc.full");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		String result = dir.getAbsolutePath();
		
		String src = (String) dirToSrc.t(dir);
		
		Map map = new HashMap();
		map.put("action","send:src");
		map.put("src",src);
		
		String r = (String) sender.t(map);
		if(r.startsWith("error:")) return result+"\n"+r;
		
		return result;
	}
}
