package a.entity.gus06.appli.gusexplorer.scheduling.persister;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.HashMap;
import java.util.Date;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180119";}
	
	public static final String KEY_PROP_FILE = "propFile";
	public static final String KEY_LAST_DATE = "last_date";
	public static final String KEY_LAST_RESULT = "last_result";


	private Service changeProp;
	private Service formatDate;

	public EntityImpl() throws Exception
	{
		changeProp = Outside.service(this,"gus06.file.properties.perform.field.putall");
		formatDate = Outside.service(this,"gus06.time.date.yyyymmdd_hhmmss");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		File propFile = (File) map.get(KEY_PROP_FILE);
		Date lastDate = (Date) map.get(KEY_LAST_DATE);
		Object lastResult = map.get(KEY_LAST_RESULT);
		
		String timeStamp = (String) formatDate.t(lastDate);
		String resultDisplay = "" + lastResult;
		
		Map m = new HashMap();
		m.put(KEY_LAST_DATE,timeStamp);
		m.put(KEY_LAST_RESULT,resultDisplay);
		
		changeProp.p(new Object[]{propFile,m});
	}
}
