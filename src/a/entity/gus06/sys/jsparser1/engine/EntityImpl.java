package a.entity.gus06.sys.jsparser1.engine;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221011";}


	private Service prepare;
	private Service resolver;
	
	public EntityImpl() throws Exception
	{
		prepare = Outside.service(this,"gus06.sys.jsparser1.prepare");
		resolver = Outside.service(this,"gus06.sys.jsparser1.resolver");
	}
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		
		Object r = prepare.t(input);
		
		T t = (T) resolver.r("block");
		return t.t(new Object[]{r, resolver});
	}
}