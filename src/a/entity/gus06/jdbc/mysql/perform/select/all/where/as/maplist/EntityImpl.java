package a.entity.gus06.jdbc.mysql.perform.select.all.where.as.maplist;

import a.framework.*;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161007";}


	private Service perform;
	private Service rsTo;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.select.all.where");
		rsTo = Outside.service(this,"gus06.jdbc.resultset.toobjectmaplist");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) perform.t(obj);
		return rsTo.t(rs);
	}
}
