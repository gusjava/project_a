package a.entity.gus06.sys.script1.access.opmap.script.file;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170310";}


	private Service getContext;
	private Service contextScriptFile;

	public EntityImpl() throws Exception
	{
		getContext = Outside.service(this,"gus06.sys.script1.access.opmap.context");
		contextScriptFile = Outside.service(this,"gus06.sys.script1.access.context.script.file");
	}

	
	public Object t(Object obj) throws Exception
	{
		Map opMap = (Map) obj;
		Map context = (Map) getContext.t(opMap);
		File scriptFile = (File) contextScriptFile.t(context);
		return scriptFile;
	}
}
