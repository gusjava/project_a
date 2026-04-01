package a.entity.gus06.jdbc.resultset.next.tostringarray;

import java.sql.ResultSet;
import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160228";}


	private Service getArray;

	public EntityImpl() throws Exception
	{getArray = Outside.service(this,"gus06.jdbc.resultset.get.stringarray");}


	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		if(!rs.next()) {rs.close();return null;}
		
		String[] array = (String[]) getArray.t(rs);
		rs.close();
		return array;
	}
}
