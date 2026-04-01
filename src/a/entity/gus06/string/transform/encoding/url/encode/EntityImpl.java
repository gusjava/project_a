package a.entity.gus06.string.transform.encoding.url.encode;

import a.framework.*;
import java.net.URLEncoder;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160417";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return URLEncoder.encode(s,"UTF-8");
	}
}
