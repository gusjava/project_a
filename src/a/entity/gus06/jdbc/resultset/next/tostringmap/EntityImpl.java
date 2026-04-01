package a.entity.gus06.jdbc.resultset.next.tostringmap;

import java.sql.ResultSet;
import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150804";}


	private Service getMap;

	public EntityImpl() throws Exception
	{getMap = Outside.service(this,"gus06.jdbc.resultset.get.stringmap");}


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		if(!rs.next()) {rs.close();return null;}
		
		Map map = (Map) getMap.t(rs);
		rs.close();
		return map;
	}
}
