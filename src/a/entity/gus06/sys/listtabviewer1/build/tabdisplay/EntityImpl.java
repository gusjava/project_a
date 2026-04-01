package a.entity.gus06.sys.listtabviewer1.build.tabdisplay;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200405";}
	
	public static final String KEY_DISPLAY = "display";
	public static final String KEY_KEY = "key";

	
	
	public Object t(Object obj) throws Exception
	{
		Map conf = (Map) obj;
		
		if(conf.containsKey(KEY_DISPLAY))
			return conf.get(KEY_DISPLAY);
		
		if(conf.containsKey(KEY_KEY))
			return conf.get(KEY_KEY);
		
		throw new Exception("Tab key not found inside conf");
	}
}
