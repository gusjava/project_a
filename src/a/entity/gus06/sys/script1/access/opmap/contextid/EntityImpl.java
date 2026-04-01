package a.entity.gus06.sys.script1.access.opmap.contextid;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170320";}


	private Service getContext;
	private Service getId;

	public EntityImpl() throws Exception
	{
		getContext = Outside.service(this,"gus06.sys.script1.access.opmap.context");
		getId = Outside.service(this,"gus06.sys.script1.access.context.id");
	}

	
	public Object t(Object obj) throws Exception
	{
		Map opMap = (Map) obj;
		Map context = (Map) getContext.t(opMap);
		return getId.t(context);
	}
}
