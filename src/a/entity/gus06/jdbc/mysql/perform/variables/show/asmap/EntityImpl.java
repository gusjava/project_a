package a.entity.gus06.jdbc.mysql.perform.variables.show.asmap;

import a.framework.*;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170129";}
	


	private Service perform;
	private Service rsToMap;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.variables.show");
		rsToMap = Outside.service(this,"gus06.jdbc.resultset.toobjectmap1");
	}
	
	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) perform.t(obj);
		return rsToMap.t(rs);
	}
}
