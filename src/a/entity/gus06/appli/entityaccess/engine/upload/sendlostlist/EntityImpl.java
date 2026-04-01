package a.entity.gus06.appli.entityaccess.engine.upload.sendlostlist;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150815";}


	private Service sender;
	private Service listToString;
	
	public EntityImpl() throws Exception
	{
		sender = Outside.service(this,"gus06.appli.entityaccess.api.sender");
		listToString = Outside.service(this,"gus06.tostring.list.join.semicolon");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list.isEmpty()) return null;
		
		String listStr = (String) listToString.t(list);
		
		Map map = new HashMap();
		map.put("action","send:lost");
		map.put("list",listStr);
		
		String r = (String) sender.t(map);
		if(r.startsWith("error:")) return r;
		return null;
	}
}
