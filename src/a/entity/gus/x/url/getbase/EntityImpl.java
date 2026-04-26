package a.entity.gus.x.url.getbase;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240117";}

	public Object t(Object obj) throws Exception
	{
		URL url = (URL) obj;
		return new URL(url.getProtocol()+"://"+url.getHost());
	}
}