package a.entity.gus06.jdbc.postgresql.perform.find.tablecollist.db;

import java.util.Set;
import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190821";}


	private Service perform;
	private Service sort;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.postgresql.perform.find.tablecolset.db");
		sort = Outside.service(this,"gus06.collection.comparator.array1.sort");
	}
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		Set set = (Set) perform.t(obj);
		List list = new ArrayList(set);
		sort.p(list);
		return list;
	}
}
