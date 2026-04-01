package a.entity.gus06.url.getbase;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201207";}

	
	public Object t(Object obj) throws Exception
	{
		URL url = (URL) obj;
		return new URL(url.getProtocol()+"://"+url.getHost());
	}
}