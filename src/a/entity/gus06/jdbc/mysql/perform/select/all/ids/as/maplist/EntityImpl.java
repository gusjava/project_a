package a.entity.gus06.jdbc.mysql.perform.select.all.ids.as.maplist;

import a.framework.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170523";}


	private Service perform;
	private Service rsTo;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.select.all.ids");
		rsTo = Outside.service(this,"gus06.jdbc.resultset.toobjectmaplist");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) perform.t(obj);
		if(rs==null) return new ArrayList();
		return rsTo.t(rs);
	}
}
