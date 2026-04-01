package a.entity.gus06.sys.filemanagement1.scan.store.preview.mode;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201106";}
	
	public static final String CONFIG_MODE = "scan.preview.mode";
	public static final String DEFAULT_VALUE = "onnotfound";
	
	public static final String VALUE_IGNORE = "ignore";
	public static final String VALUE_REWRITE = "rewrite";
	public static final String VALUE_ONNOTFOUND = "onnotfound";
	public static final String VALUE_SKIPFIRST = "skipfirst";


	private boolean skipFlag = false;

	public EntityImpl() throws Exception
	{
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String md5 = (String) o[1];
		
		String mode = getMode(engine);
		
		if(mode.equals(VALUE_REWRITE)) return true;
		if(mode.equals(VALUE_IGNORE)) return false;
		if(mode.equals(VALUE_ONNOTFOUND)) return onNotFound(engine,md5);
		if(mode.equals(VALUE_SKIPFIRST))
		{
			if(!onNotFound(engine,md5)) return false;
			if(!skipFlag) {skipFlag = true;return false;}
			return true;
		}
		
		throw new Exception("Unsupported mode: "+mode);
	}
	
	
	private boolean onNotFound(Object engine, String md5) throws Exception
	{
		File file = (File) ((R) engine).r("previewFile:"+md5);
		return !file.exists();
	}
	
	
	
	private String getMode(Object engine) throws Exception
	{
		String mode = (String) ((R)engine).r("config:"+CONFIG_MODE);
		return mode!=null ? mode : DEFAULT_VALUE;
	}
}