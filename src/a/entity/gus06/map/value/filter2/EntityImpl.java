package a.entity.gus06.map.value.filter2;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151125";}
	
	
	private Service mapFilter;
	private Service filterBuilder;
	
	public EntityImpl() throws Exception
	{
		mapFilter = Outside.service(this,"gus06.map.value.filter");
		filterBuilder = Outside.service(this,"gus06.filter.string.simple1.one");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		String rule = (String) o[1];
		
		if(rule.equals("")) return map;

		F filter = (F) filterBuilder.t(rule);
		return mapFilter.t(new Object[]{map,filter});
	}
}