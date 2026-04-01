package a.entity.gus06.jdbc.mysql.perform.select.all.ids1.as.mapmap;

import a.framework.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170523";}


	private Service perform;
	private Service findPrimaryKey;
	private Service rsTo;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.select.all.ids1.perform");
		findPrimaryKey = Outside.service(this,"gus06.jdbc.mysql.perform.table.findprimarykey.strict");
		rsTo = Outside.service(this,"gus06.jdbc.resultset.toobjectmapmap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Collection ids = (Collection) o[2];
		
		if(ids.isEmpty()) return new HashMap();
		
		String idCol = (String) findPrimaryKey.t(new Object[]{cx,path});
		ResultSet rs = (ResultSet) perform.t(new Object[]{cx,path,ids,idCol});
		return rsTo.t(new Object[]{rs,idCol});
	}
}
