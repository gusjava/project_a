package a.entity.gus06.jdbc.mysql.perform.select.c1.as.set;

import a.framework.*;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160503";}


	private Service perform;
	private Service rsToSet;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.select.c1");
		rsToSet = Outside.service(this,"gus06.jdbc.resultset.toobjectset");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) perform.t(obj);
		return rsToSet.t(rs);
	}
}
