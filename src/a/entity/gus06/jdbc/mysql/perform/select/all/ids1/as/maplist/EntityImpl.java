package a.entity.gus06.jdbc.mysql.perform.select.all.ids1.as.maplist;

import a.framework.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170523";}


	private Service perform;
	private Service findPrimaryKey;
	private Service rsTo;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.select.all.ids1.perform");
		findPrimaryKey = Outside.service(this,"gus06.jdbc.mysql.perform.table.findprimarykey.strict");
		rsTo = Outside.service(this,"gus06.jdbc.resultset.toobjectmaplist");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Collection ids = (Collection) o[2];
		
		if(ids.isEmpty()) return new ArrayList();
		
		String idCol = (String) findPrimaryKey.t(new Object[]{cx,path});
		ResultSet rs = (ResultSet) perform.t(new Object[]{cx,path,ids,idCol});
		return rsTo.t(rs);
	}
}
