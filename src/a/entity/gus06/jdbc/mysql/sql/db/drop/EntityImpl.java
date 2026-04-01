package a.entity.gus06.jdbc.mysql.sql.db.drop;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141009";}
	
	
		
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		return "DROP DATABASE IF EXISTS "+name;
	}
}
