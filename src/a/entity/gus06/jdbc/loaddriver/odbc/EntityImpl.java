package a.entity.gus06.jdbc.loaddriver.odbc;

import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20171026";}

	public static final String PATH = "sun.jdbc.odbc.JdbcOdbcDriver";

	public EntityImpl() throws Exception
	{
		Outside.service(this,"gus06.jdbc.loaddriver").p(PATH);
	}
}
