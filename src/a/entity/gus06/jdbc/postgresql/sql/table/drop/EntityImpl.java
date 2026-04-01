package a.entity.gus06.jdbc.postgresql.sql.table.drop;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190726";}


	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.postgresql.format.sql.name");}

	
		
	public Object t(Object obj) throws Exception
	{
		String path = (String) obj;
		return "DROP TABLE IF EXISTS "+format(path);
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
