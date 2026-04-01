package a.entity.gus06.filter.map.build.fromstring.rule1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}


	private Service parseRule;
	private Service filterRule1;
	private Service andFields1;


	public EntityImpl() throws Exception
	{
		parseRule = Outside.service(this,"gus06.map.string.stringtomap.parser.semicolon");
		filterRule1 = Outside.service(this,"gus06.filter.string.build.rule1");
		andFields1 = Outside.service(this,"gus06.filter.map.build.andfields1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String rule = (String) obj;
		Map map = (Map) parseRule.t(rule);
		Map fmap = buildMapFilter(map);
		return andFields1.t(fmap);
	}
	
	
	
	private Map buildMapFilter(Map map) throws Exception
	{
		Map m = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String rule = (String) map.get(key);
			F filter = (F) filterRule1.t(rule);
			m.put(key,filter);
		}
		return m;
	}
}
