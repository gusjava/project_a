package a.entity.gus06.jdbc.loaddriver.h2;

import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20250720";}

	public static final String PATH = "org.h2.Driver";

	public EntityImpl() throws Exception
	{
//		Class.forName("org.h2.Driver");
		Outside.service(this,"gus06.jdbc.loaddriver").p(PATH);
	}
}