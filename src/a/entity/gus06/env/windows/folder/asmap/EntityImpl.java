package a.entity.gus06.env.windows.folder.asmap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170405";}
	
	public static final String KEY_DESKTOP = "desktop";
	public static final String KEY_STARTUP = "startup";

	private Service desktop;
	private Service startup;


	public EntityImpl() throws Exception
	{
		desktop = Outside.service(this,"gus06.env.windows.folder.desktop");
		startup = Outside.service(this,"gus06.env.windows.folder.startup");
	}
	
	public Object g() throws Exception
	{
		Map map = new HashMap();
		map.put(KEY_DESKTOP,desktop.g());
		map.put(KEY_STARTUP,startup.g());
		return map;
	}
}
