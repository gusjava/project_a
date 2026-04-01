package a.entity.gus06.appli.gusappmonitor.logger;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.Date;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190411";}


	private Service logToDir;

	public static final String INFO_BUILD_ID = "build_id";
	public static final String INFO_BUILD_TIME = "build_time";
	
	
	public EntityImpl() throws Exception
	{
		logToDir = Outside.service(this,"gus06.sys.logger1.dir.write.yyyymm");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Map infoMap = (Map) o[1];
		String eventType = (String) o[2];
		Date date = (Date) o[3];
		
		String buildId = (String) infoMap.get(INFO_BUILD_ID);
		String buildTime = (String) infoMap.get(INFO_BUILD_TIME);
		
		String line = buildId+"|"+buildTime+":"+eventType;
		
		logToDir.p(new Object[]{dir,date,line});
	}
}
