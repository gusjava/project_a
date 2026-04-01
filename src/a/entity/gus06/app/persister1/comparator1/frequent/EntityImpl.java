package a.entity.gus06.app.persister1.comparator1.frequent;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, R, V {

	public String creationDate() {return "20160511";}


	private Service persistMap;
	private Service mapToComparator;


	public EntityImpl() throws Exception
	{
		persistMap = Outside.service(this,"gus06.app.persister1.data.map");
		mapToComparator = Outside.service(this,"gus06.convert.maptocomparator");
	}
	
	
	public Object r(String key) throws Exception
	{
		Map m = (Map) persistMap.r(key);
		if(m==null) m = new HashMap();
		return mapToComparator.t(m);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		Map m = (Map) persistMap.r(key);
		if(m==null) m = new HashMap();
		
		increase(m,(String) obj);
		persistMap.v(key,m);
	}
	
	
	private void increase(Map m, String value)
	{
		if(!m.containsKey(value))
			m.put(value,"1");
		else
		{
			Object o = m.get(value);
			m.put(value,""+(int_(o)+1));
		}
	}
	
	private int int_(Object o)
	{return Integer.parseInt(""+o);}
}
