package a.entity.gus06.jdbc.loaddriver.postgresql;

import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20190715";}

	public static final String PATH = "org.postgresql.Driver";

	public EntityImpl() throws Exception
	{
		Outside.service(this,"gus06.jdbc.loaddriver").p(PATH);
	}
}
