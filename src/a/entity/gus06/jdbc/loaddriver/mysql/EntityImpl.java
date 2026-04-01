package a.entity.gus06.jdbc.loaddriver.mysql;

import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20141006";}

	public static final String PATH = "com.mysql.jdbc.Driver";

	public EntityImpl() throws Exception
	{
		Outside.service(this,"gus06.jdbc.loaddriver").p(PATH);
	}
}
