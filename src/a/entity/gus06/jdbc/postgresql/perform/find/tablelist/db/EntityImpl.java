package a.entity.gus06.jdbc.postgresql.perform.find.tablelist.db;

import java.util.Set;
import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230303";}


	private Service perform;
	

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.postgresql.perform.find.tableset.db");
	}
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		Set set = (Set) perform.t(obj);
		List list = new ArrayList(set);
		Collections.sort(list);
		return list;
	}
}