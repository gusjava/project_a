package a.entity.gus06.jdbc.mysql.sql.select.count;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161008";}
	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}
	
		
	public Object t(Object obj) throws Exception
	{return "SELECT COUNT(*) FROM "+format(obj);}
	
	private String format(Object path) throws Exception
	{return (String) format.t(path);}
}