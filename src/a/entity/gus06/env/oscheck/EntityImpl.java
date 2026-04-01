package a.entity.gus06.env.oscheck;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191126";}


	public static final String  OSName = System.getProperty("os.name").toLowerCase();
	
	public static final boolean isMac	= OSName.contains("mac");
	public static final boolean isLinux	= OSName.contains("linux");
	public static final boolean isWindows	= OSName.contains("windows");
	

	public boolean f(Object obj) throws Exception
	{
		String os = (String) obj;
		
		if(os.equalsIgnoreCase("mac")) return isMac;
		if(os.equalsIgnoreCase("linux")) return isLinux;
		if(os.equalsIgnoreCase("windows")) return isWindows;
		
		throw new Exception("Unknown os name: "+os);
	}
}
