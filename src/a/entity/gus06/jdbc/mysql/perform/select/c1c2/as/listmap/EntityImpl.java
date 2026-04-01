package a.entity.gus06.jdbc.mysql.perform.select.c1c2.as.listmap;

import a.framework.*;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160503";}


	private Service perform;
	private Service rsToListMap;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.select.c1c2");
		rsToListMap = Outside.service(this,"gus06.jdbc.resultset.toobjectlistmap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) perform.t(obj);
		return rsToListMap.t(rs);
	}
}
