package a.entity.gus06.sys.expression1.apply.op._jdbc_dblist;

import a.framework.*;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231112";}


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.jdbc.generic.perform.find.dbset");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Connection) return toList((Set) find.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private List toList(Set set)
	{
		List list = new ArrayList(set);
		Collections.sort(list);
		return list;
	}
}
