package a.entity.gus06.sys.script1.access.opmap.script.dir;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170310";}


	private Service getContext;
	private Service contextScriptDir;

	public EntityImpl() throws Exception
	{
		getContext = Outside.service(this,"gus06.sys.script1.access.opmap.context");
		contextScriptDir = Outside.service(this,"gus06.sys.script1.access.context.script.dir");
	}

	
	public Object t(Object obj) throws Exception
	{
		Map opMap = (Map) obj;
		Map context = (Map) getContext.t(opMap);
		File scriptDir = (File) contextScriptDir.t(context);
		return scriptDir;
	}
}
