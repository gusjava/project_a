package a.entity.gus06.sys.ruleobj1.build.url;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170117";}

	
	public Object t(Object obj) throws Exception
	{
		return new URL((String) obj);
	}
}
