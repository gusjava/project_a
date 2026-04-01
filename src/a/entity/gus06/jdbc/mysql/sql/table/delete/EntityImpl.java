package a.entity.gus06.jdbc.mysql.sql.table.delete;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150807";}

	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}



	public Object t(Object obj) throws Exception
	{
		String path = (String) obj;
		return "DELETE FROM "+format(path);
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
