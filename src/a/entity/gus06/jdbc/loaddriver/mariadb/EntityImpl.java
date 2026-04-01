package a.entity.gus06.jdbc.loaddriver.mariadb;

import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20230220";}

	public static final String PATH = "org.mariadb.jdbc.Driver";

	public EntityImpl() throws Exception
	{
		Outside.service(this,"gus06.jdbc.loaddriver").p(PATH);
	}
}