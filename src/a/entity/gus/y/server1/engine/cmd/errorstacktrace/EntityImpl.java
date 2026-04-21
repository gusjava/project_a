package a.entity.gus.y.server1.engine.cmd.errorstacktrace;

import java.util.List;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260420";}

	private Service getArg;
	private List errList;

	public EntityImpl() throws Exception
	{
		getArg = Outside.service(this,"gus.y.server1.tool.payload.args.fullstring");
		errList = (List) Outside.resource(this, "errlist");
	}

	public Object t(Object obj) throws Exception
	{
		int pos = Integer.parseInt("" + getArg.t(obj));
		Object[] o = (Object[]) errList.get(pos);
		Throwable t = (Throwable) o[3];
		return stackTraceToList(t);
	}
	
	private List stackTraceToList(Throwable t)
	{
		List list = new ArrayList();
		for (StackTraceElement ste : t.getStackTrace()) list.add(ste.toString());
		return list;
	}
}
