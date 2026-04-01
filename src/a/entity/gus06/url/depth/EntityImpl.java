package a.entity.gus06.url.depth;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170113";}
	
	
	public Object t(Object obj) throws Exception
	{
		URL url = (URL) obj;
		String path = url.getPath();
		int depth = path.split("/").length;
		
		return Integer.valueOf(depth);
	}
}
