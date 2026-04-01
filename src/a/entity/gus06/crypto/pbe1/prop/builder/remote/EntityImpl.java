package a.entity.gus06.crypto.pbe1.prop.builder.remote;

import a.framework.*;
import java.util.Map;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150625";}


	private Service decrypt;
	private Service urlToProperties;
	
	public EntityImpl() throws Exception
	{
		decrypt = Outside.service(this,"gus06.crypto.pbe1.object.decrypt");
		urlToProperties = Outside.service(this,"gus06.convert.urltoproperties");
	}
	
	public Object t(Object obj) throws Exception
	{
		URL url = new URL((String) obj);
		Map p = (Map) urlToProperties.t(url);
		return decrypt.t(p);
	}
}
