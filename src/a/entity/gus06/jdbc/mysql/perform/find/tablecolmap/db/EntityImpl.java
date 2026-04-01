package a.entity.gus06.jdbc.mysql.perform.find.tablecolmap.db;

import java.util.Set;
import a.framework.*;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190821";}


	private Service perform;
	private Service putInSet;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.find.tablecolset.db");
		putInSet = Outside.service(this,"gus06.map.put.inset");
	}
	

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		Set set = (Set) perform.t(obj);
		
		Map map = new HashMap();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String[] elem = (String[]) it.next();
			putInSet.p(new Object[]{map,elem[0],elem[1]});
		}
		return map;
	}
}
