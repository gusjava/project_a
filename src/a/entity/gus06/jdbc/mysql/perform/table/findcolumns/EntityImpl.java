package a.entity.gus06.jdbc.mysql.perform.table.findcolumns;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160409";}

	public static final String FIELD = "COLUMN_NAME";


	private Service describe;
	private Service toObjectMapList;

	public EntityImpl() throws Exception
	{
		describe = Outside.service(this,"gus06.jdbc.mysql.perform.table.describe");
		toObjectMapList = Outside.service(this,"gus06.jdbc.resultset.toobjectmaplist");
	}



	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		Connection cx = (Connection) t[0];
		String path = (String) t[1];
		
		ResultSet rs = (ResultSet) describe.t(new Object[]{cx,path});
		List list = (List) toObjectMapList.t(rs);
		
		Set set = new HashSet();
		for(int i=0;i<list.size();i++)
		{
			Map m = (Map) list.get(i);
			set.add(m.get(FIELD));
		}
		return set;
	}
}
