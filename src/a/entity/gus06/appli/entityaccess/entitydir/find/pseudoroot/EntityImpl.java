package a.entity.gus06.appli.entityaccess.entitydir.find.pseudoroot;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150304";}


	public static final String KEY_WEBAPI_USER = "webapi_user";
	
	
	private Service findEntityDir;
	private Service optionManager;
	
	
	
	public EntityImpl() throws Exception
	{
		findEntityDir = Outside.service(this,"gus06.appli.entityaccess.entitydir.find.entityroot");
		optionManager = Outside.service(this,"gus06.sys.option.manager");
	}
	
	
	public Object g() throws Exception
	{
		File dir = (File) findEntityDir.g();
		if(dir==null) return null;
		return new File(dir,option(KEY_WEBAPI_USER));
	}
	
	
	
	private String option(String key) throws Exception
	{
		String v = (String) optionManager.r(key);
		if(v==null) throw new Exception("Undefined option: "+key);
		return v;
	}
}
