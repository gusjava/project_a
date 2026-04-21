package a.entity.gus.y.server1.engine.cmd.errornumber;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260420";}

	private List errList;

	public EntityImpl() throws Exception
	{
		errList = (List) Outside.resource(this, "errlist");
	}

	public Object t(Object obj) throws Exception
	{return errList.size();}
}
