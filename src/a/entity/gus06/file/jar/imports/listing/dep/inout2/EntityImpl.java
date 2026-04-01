package a.entity.gus06.file.jar.imports.listing.dep.inout2;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.jar.imports.listing.dep.inout");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List[] lists = (List[]) perform.t(obj);
		
		Map map = new HashMap();
		map.put("in",lists[0]);
		map.put("out",lists[1]);
		
		return map;
	}
}
