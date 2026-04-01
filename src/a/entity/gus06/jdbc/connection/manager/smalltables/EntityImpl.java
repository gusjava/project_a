package a.entity.gus06.jdbc.connection.manager.smalltables;

import a.framework.*;
import java.sql.Connection;
import java.util.Set;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150623";}


	private Service perform;
	private HashMap map;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.find.pathset.smalltable");
		map = new HashMap();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		G holder = (G) obj;
		if(!map.containsKey(holder))
			map.put(holder,holderToSet(holder));
		return map.get(holder);
	}
	
	
	
	private Set holderToSet(G holder) throws Exception
	{
		Connection cx = (Connection) holder.g();
		return (Set) perform.t(cx);
	}
}
