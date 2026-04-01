package a.entity.gus06.sys.expert1.analyze.bool.iftrue;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160811";}


	private Service equality;

	public EntityImpl() throws Exception
	{
		equality = Outside.service(this,"gus06.sys.expert1.analyze.bool.iftrue.equality");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Map m = (Map) obj;
		
		Map analyze = (Map) m.get("analyze");
		String operator = (String) analyze.get("operator");
		Object content = analyze.get("content");
		
		if(operator.equals("=="))
		{
			List l = (List) content;
			Object part1 = l.get(0);
			Object part2 = l.get(1);
			
			return equality.f(new Object[]{m,part1,part2});
		}
		return false;
	}
}
