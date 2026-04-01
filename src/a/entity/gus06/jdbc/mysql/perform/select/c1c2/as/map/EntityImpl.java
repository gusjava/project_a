package a.entity.gus06.jdbc.mysql.perform.select.c1c2.as.map;

import a.framework.*;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160503";}


	private Service perform;
	private Service rsToMap;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.select.c1c2");
		rsToMap = Outside.service(this,"gus06.jdbc.resultset.toobjectmap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) perform.t(obj);
		return rsToMap.t(rs);
	}
}
