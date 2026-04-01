package a.entity.gus06.sys.jsonparser1.evaluate;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151125";}


	private Service prepare;
	private Service resolver;
	
	
	public EntityImpl() throws Exception
	{
		prepare = Outside.service(this,"gus06.sys.parser3.prepare");
		resolver = Outside.service(this,"gus06.sys.jsonparser1.resolver");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List l = (List) prepare.t(obj);
		return resolver.t(l);
	}
}
