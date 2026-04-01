package a.entity.gus06.appli.vindinium.data.retrievedata.initurl;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	public static final String URL_ROOT = "http://vindinium.org";
	public static final String URL_TRAINING = URL_ROOT+"/api/training";
	public static final String URL_ARENA = URL_ROOT+"/api/arena";
	public static final String URL_LOCAL = "engine";


	public Object t(Object obj) throws Exception
	{return initUrl((String) obj);}

	private String initUrl(String mode) throws Exception
	{
		if(mode==null) throw new Exception("Undefined mode: null");
		if(mode.equals(MODE.ARENA)) return URL_ARENA;
		if(mode.equals(MODE.TRAINING)) return URL_TRAINING;
		if(mode.equals(MODE.LOCAL)) return URL_LOCAL;
		
		throw new Exception("Unknown mode: "+mode);
	}
}
