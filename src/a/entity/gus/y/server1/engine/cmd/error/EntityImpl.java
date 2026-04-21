package a.entity.gus.y.server1.engine.cmd.error;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260420";}

	private Service getArg;
	private Service makeSerializable;
	private List errList;

	public EntityImpl() throws Exception
	{
		getArg = Outside.service(this,"gus.y.server1.tool.payload.args.fullstring");
		makeSerializable = Outside.service(this,"gus.x.json.makeserialisable");
		errList = (List) Outside.resource(this, "errlist");
	}

	public Object t(Object obj) throws Exception
	{
		int pos = Integer.parseInt("" + getArg.t(obj));
		if(pos<0) pos += errList.size();
		
		Object err = errList.get(pos);
		return makeSerializable.t(err);
	}
}
