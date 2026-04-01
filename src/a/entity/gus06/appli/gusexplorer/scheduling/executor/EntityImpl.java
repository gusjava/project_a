package a.entity.gus06.appli.gusexplorer.scheduling.executor;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180119";}
	
	public static final String KEY_SCRIPT_FILE = "scriptFile";


	private Service buildE;

	public EntityImpl() throws Exception
	{
		buildE = Outside.service(this,"gus06.sys.script1.build1.e");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		File f = (File) map.get(KEY_SCRIPT_FILE);
		E execute = (E) buildE.t(f);
		execute.e();
	}
}
