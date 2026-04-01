package a.entity.gus06.jdbc.loaddriver.oracle;

import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20171026";}

	public static final String PATH = "oracle.jdbc.driver.OracleDriver";

	public EntityImpl() throws Exception
	{
		Outside.service(this,"gus06.jdbc.loaddriver").p(PATH);
	}
}
