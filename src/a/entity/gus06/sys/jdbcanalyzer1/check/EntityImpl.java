package a.entity.gus06.sys.jdbcanalyzer1.check;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.sql.Connection;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170517";}


	private Service selectC1;

	public EntityImpl() throws Exception
	{
		selectC1 = Outside.service(this,"gus06.jdbc.mysql.perform.select.c1.as.set");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return check((R) obj);
	}
	
	private Map check(R engine) throws Exception
	{
		Connection cx = (Connection) engine.r("cx");
		String db = (String) engine.r("db");
		Map pkmap1 = (Map) engine.r("pkmap1");
		Map fkmap0 = (Map) engine.r("fkmap0");
		
		if(fkmap0==null) throw new Exception("fkmap0 not initialized yet");
		
		List errors = new ArrayList();
		
		Iterator it = fkmap0.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String refTable = (String) fkmap0.get(key);
			
			String[] n = key.split("@",2);
			String table = n[0];
			String column = n[1];
			
			
			if(!pkmap1.containsKey(refTable))
			{
				errors.add(key+"="+refTable+" : pk not found for ref table");
				continue;
			}
			String refColumns = (String) pkmap1.get(refTable);
			
			Set values = (Set) selectC1.t(new Object[]{cx,db+"."+table,column});
			Set refValues = findRefValues(cx,db,refTable,refColumns);
			
			values.remove(null);
			refValues.remove(null);
			
			Iterator it1 = values.iterator();
			while(it1.hasNext())
			{
				Object value = it1.next();
				if(!refValues.contains(value))
				errors.add(key+"="+refTable+" : unreferenced value: "+value);
			}
		}
		
		boolean success = errors.isEmpty();
		Map map = new HashMap();
		map.put("success",Boolean.valueOf(success));
		if(!success) map.put("errors",errors);
		return map;
	}
	
	
	
	private Set findRefValues(Connection cx, String db, String refTable, String refColumns) throws Exception
	{
		Set set = new HashSet();
		String[] nn = refColumns.split(";");
		for(String n:nn)
		{
			Set s = (Set) selectC1.t(new Object[]{cx,db+"."+refTable,n});
			set.addAll(s);
		}
		return set;
	}
}
